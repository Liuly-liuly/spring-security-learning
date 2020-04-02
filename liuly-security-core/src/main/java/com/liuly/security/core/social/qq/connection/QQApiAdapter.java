package com.liuly.security.core.social.qq.connection;

import com.liuly.security.core.social.qq.api.QQGetUserInfo;
import com.liuly.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/4
 * @since JDK 1.8
 */
public class QQApiAdapter implements ApiAdapter<QQGetUserInfo> {
    @Override
    public boolean test(QQGetUserInfo api) {
        return true;
    }

    //适配器就是把所得到的相应的信息数据结构转化为OAuth2协议中标准的信息数据结构中去
    @Override
    public void setConnectionValues(QQGetUserInfo api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);//这个是个人主页
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQGetUserInfo api) {
        return null; //获取个人主页
    }

    @Override
    public void updateStatus(QQGetUserInfo api, String message) {
        //想主页跟新消息
    }
}
