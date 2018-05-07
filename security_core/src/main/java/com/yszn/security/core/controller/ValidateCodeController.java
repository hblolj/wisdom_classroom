package com.yszn.security.core.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yszn.security.core.constant.CacheKeyConstants;
import com.yszn.security.core.constant.SecurityConstants;
import com.yszn.security.core.properties.base.SecurityProperties;
import com.yszn.security.core.service.SysCacheService;
import com.yszn.security.core.service.impl.RedisServiceImpl;
import com.yszn.security.core.support.JsonData;
import com.yszn.security.core.util.TimeUtils;
import com.yszn.security.core.validate.code.ValidateCodeProcessor;
import com.yszn.security.core.validate.code.ValidateCodeProcessorHolder;
import com.yszn.security.core.validate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hblolj
 * @Date: 2018/5/3 16:06
 * @Description: 验证码创建、发送接口
 * @Version: 1.0
 **/
@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private SysCacheService sysCacheService;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 按照 type 创建不同的验证码处理器，{@link ValidateCodeProcessor}
     * @param request
     * @param response
     * @param type
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public JsonData<Boolean> createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type){
        // TODO: 2018/5/3 短信资费保护策略
        // TODO: 2018/5/4 只有当 type 为 sms 时，才进行保护
        // 1. 对用户访问改接口进行限制频率
        // 2. 对用户当天访问次数进行限制
        // 3. 用户表示采用手机号 + deviceId 双重限制
        // 4. 考虑在前端采用图形验证码或其他形式进行保护
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        try {
            if (StringUtils.equalsIgnoreCase(ValidateCodeType.SMS.toString(), type)){
                String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
                String mobile = ServletRequestUtils.getRequiredStringParameter(webRequest.getRequest(), paramName);
                String fromCache = sysCacheService.getFromCache(CacheKeyConstants.PHONE_CAN_SEND_COUNT_IN_ONE_DAY);
                ConcurrentHashMap<String, Integer> map = objectMapper.readValue(fromCache,
                        new TypeReference<ConcurrentHashMap<String, Integer>>(){});
                map = map == null ? new ConcurrentHashMap<>(16) : map;
                Integer number = map.get(mobile);
                if ((number == null ? 0 : number) < securityProperties.getCode().getSms().getCount()){
                    //可以发送
                    Boolean sendResult = validateCodeProcessorHolder.findValidateCodeProcessor(type).create(webRequest);
                    if (sendResult){
                        map.put(mobile, number == null ? 1 : number + 1);
                        sysCacheService.saveCache(objectMapper.writeValueAsString(map),
                                TimeUtils.getSecondsNextEarlyMorning().intValue(),
                                CacheKeyConstants.PHONE_CAN_SEND_COUNT_IN_ONE_DAY);
                        return new JsonData<>(0, "短信验证码发送成功!", true);
                    }
                }
                return new JsonData<>(1, "短信验证码发送频繁!", false);
            }else if (StringUtils.equalsIgnoreCase(ValidateCodeType.IMAGE.toString(), type)){
                Boolean sendResult = validateCodeProcessorHolder.findValidateCodeProcessor(type).create(webRequest);
                return null;
            }
        } catch (ServletRequestBindingException e) {
            logger.error(e.getMessage(), e);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return new JsonData<>(1, "验证码发送失败!", false);
    }
}
