package com.liuly.security.core.social.weixin.api;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/5
 * @since JDK 1.8
 */
public interface Weixin {

    WeixinUserInfo getWeixinUserInfo(String openId);
}
