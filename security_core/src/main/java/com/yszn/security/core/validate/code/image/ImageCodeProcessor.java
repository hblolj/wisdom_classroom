package com.yszn.security.core.validate.code.image;

import com.yszn.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author: hblolj
 * @Date: 2018/5/4 8:58
 * @Description: 图形验证码处理器
 * @Version: 1.0
 **/
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode>{

    /**
     * 发送图形验证码，将其写到响应中
     * @param request
     * @param validateCode
     * @return
     * @throws Exception
     */
    @Override
    protected Boolean send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        return ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
