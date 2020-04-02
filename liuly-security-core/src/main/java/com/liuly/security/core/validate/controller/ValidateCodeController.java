package com.liuly.security.core.validate.controller;

import com.liuly.security.core.validate.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String , ValidateCodeProcessor> validateCodeProcessorMap;

    @GetMapping("/code/{codeType}")
    public void  createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("codeType") String type) throws Exception{
        validateCodeProcessorMap.get(type+"CodeValidateProcessor").create(new ServletWebRequest(request,response));
    }

  /*
    private static String SESSION_KEY_CODE = "SEESION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

  @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generatorCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_CODE, imageCode);
        ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void smsCode(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletRequestBindingException {
        ValidateCode smsCode =  smsCodeGenerator.generatorCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_CODE, smsCode);
        String mobile = ServletRequestUtils.getStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }*/

}
