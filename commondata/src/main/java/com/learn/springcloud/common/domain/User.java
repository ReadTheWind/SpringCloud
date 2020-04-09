package com.learn.springcloud.common.domain;

import java.io.Serializable;

/**
 * @author: liuhuan
 * @Desc: 用户信息
 * @time: 2020/4/7 9:42
 */
public class User implements Serializable {

    private String userName;

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
