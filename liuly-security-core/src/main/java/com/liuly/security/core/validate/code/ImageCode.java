package com.liuly.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage, String code, int expireTime) {
        super(code,expireTime);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

}
