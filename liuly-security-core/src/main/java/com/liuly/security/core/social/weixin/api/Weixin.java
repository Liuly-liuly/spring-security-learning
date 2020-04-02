package com.liuly.security.core.social.weixin.api;

/**
 * @version 1.0
 * @Description: li.liu06@hand-china.com
 * @Auther: Liuly
 * @Date: 2018/10/5
 * @since JDK 1.8
 */
public interface Weixin {

    WeixinUserInfo getWeixinUserInfo(String openId);
}
