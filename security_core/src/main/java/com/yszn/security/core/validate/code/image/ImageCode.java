package com.yszn.security.core.validate.code.image;

import com.yszn.security.core.validate.code.base.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: hblolj
 * @Date: 2018/5/4 8:50
 * @Description: 图形验证码实体类
 * @Version: 1.0
 **/
public class ImageCode extends ValidateCode implements Serializable{

    private static final long serialVersionUID = 5061385007932572151L;
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
