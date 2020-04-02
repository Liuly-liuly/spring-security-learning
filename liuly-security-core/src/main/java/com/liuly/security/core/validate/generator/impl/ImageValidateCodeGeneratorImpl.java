package com.liuly.security.core.validate.generator.impl;

import com.liuly.security.core.properties.SecurityProperties;
import com.liuly.security.core.properties.ValidateCodeProperties;
import com.liuly.security.core.validate.code.ImageCode;
import com.liuly.security.core.validate.filter.ValidateCodeFilter;
import com.liuly.security.core.validate.generator.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
public class ImageValidateCodeGeneratorImpl implements ValidateCodeGenerator {

    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};

    private Random r = new Random();

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generatorCode(HttpServletRequest request) {
        // 随机数生成类
        Random random = new Random();
        int size = 5;
        // 定义变量保存生成的验证码
        String vCode = "";
        char c;
        // 产生验证码
        for (int i = 0; i < size; i++) {
            // 产生一个26以内的随机整数
            int number = random.nextInt(26);
            // 如果生成的是偶数，则随机生成一个数字
            if (number % 2 == 0) {
                c = (char) ('0' + (char) ((int) (Math.random() * 10)));
                // 如果生成的是奇数，则随机生成一个字母
            } else {
                c = (char) ((char) ((int) (Math.random() * 26)) + 'A');
            }
            vCode = vCode + c;
        }
        // 验证码图片的生成
        // 定义图片的宽度和高度
        int w = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getCode().getImage().getWidth());
        int h = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getCode().getImage().getHeight());
        int _size = securityProperties.getCode().getImage().getSize();
        int width = (int) Math.ceil(size * 20);
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图片的上下文
        Graphics gr = image.getGraphics();
        // 设定图片背景颜色
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, width, height);
        // 设定图片边框
        gr.setColor(Color.GRAY);
        gr.drawRect(0, 0, width - 1, height - 1);
        // 画十条干扰线
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            gr.setColor(randomColor());
            gr.drawLine(x1, y1, x2, y2);
        }
        // 设置字体，画验证码
        gr.setColor(randomColor());
        gr.setFont(randomFont());
        gr.drawString(vCode, 10, 22);
        // 图像生效
        gr.dispose();
        System.out.println("-->"+w+"--->"+h+"--->"+_size+"--->"+securityProperties.getCode().getImage().getExpireIn());
        return new ImageCode(image, vCode, securityProperties.getCode().getImage().getExpireIn());
    }

    // 生成随机的颜色
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    // 生成随机的字体
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];// 生成随机的字体名称
        int style = r.nextInt(4);
        int size = r.nextInt(3) + 24; // 生成随机字号, 24 ~ 28
        return new Font(fontName, style, size);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
