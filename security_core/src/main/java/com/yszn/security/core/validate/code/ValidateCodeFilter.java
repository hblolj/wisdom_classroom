package com.yszn.security.core.validate.code;

import com.yszn.security.core.constant.SecurityConstants;
import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: hblolj
 * @Date: Create in 14:37 2017/10/30
 * @Description: 校验验证码的过滤器
 * */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 验证请求 url 与配置的 url 是否匹配的工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 系统中校验码处理器的管理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 初始化要拦截的url的配置信息
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

//        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_BIND_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);

    }

    /**
     * 将系统中配置的需要校验验证码的 URL 根据校验类型放入 map
     * @param url
     * @param type
     */
    private void addUrlToMap(String url, ValidateCodeType type) {
        if (StringUtils.isBlank(url)){
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
            for (String u : urls){
                urlMap.put(u, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType codeType = getValidateCodeType(httpServletRequest);
        if (codeType != null){
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(codeType)
                        .validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
            }catch (ValidateCodeException exception){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, exception);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request){
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")){
            Set<String> urls = urlMap.keySet();
            String contextPath = request.getContextPath();
            String requestURI = request.getRequestURI();
            for (String url : urls){
                if (antPathMatcher.match(contextPath + "/" + url, requestURI)){
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
