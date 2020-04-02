package com.liuly.security.browser.system;

import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/**
 * @version 1.0
 * @Description: 1367636569@qq.com
 * @Auther: Liuly
 * @Date: 2018/10/1
 * @since JDK 1.8
 */
public class ResponseData {

    private  Object content ;

    public ResponseData(){}

    public ResponseData(Object data){
        this.content = data;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
