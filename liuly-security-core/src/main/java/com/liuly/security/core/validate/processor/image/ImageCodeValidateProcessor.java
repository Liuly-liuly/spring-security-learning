package com.liuly.security.core.validate.processor.image;

import com.liuly.security.core.validate.code.ImageCode;
import com.liuly.security.core.validate.processor.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/3
 * @since JDK 1.8
 */
@Component
public class ImageCodeValidateProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getBufferedImage(), "JPEG", request.getResponse().getOutputStream());
        }
}
