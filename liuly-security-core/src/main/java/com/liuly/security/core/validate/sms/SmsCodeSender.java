package com.liuly.security.core.validate.sms;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/2
 * @since JDK 1.8
 */
public interface SmsCodeSender {

    void  send (String mobile , String content);
}
