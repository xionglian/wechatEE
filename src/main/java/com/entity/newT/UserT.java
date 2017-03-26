package com.entity.newT;

/**
 * Created by zengqin on 2017/3/25.
 */
public class UserT {
    private String openId;
    private String userName;

    public UserT(String openId, String userName) {
        this.openId = openId;
        this.userName = userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

