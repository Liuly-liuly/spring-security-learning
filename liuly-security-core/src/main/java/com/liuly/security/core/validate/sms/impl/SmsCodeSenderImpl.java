package com.liuly.security.core.validate.sms.impl;

import com.liuly.security.core.validate.sms.SmsCodeSender;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
public class SmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public void send(String mobile, String content) {
        System.out.println("发送的内容---->"+content+"手机号--->"+mobile);
    }
}
