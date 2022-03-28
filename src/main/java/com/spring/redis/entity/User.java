package com.spring.redis.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int mUserid;
    private String mUserName;
    private String mPassword;
    private String mName;
    private String mTelephone;

    public int getUserid() {
        return mUserid;
    }

    public void setUserid(int mUserid) {
        this.mUserid = mUserid;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getAclName() {
        return mName;
    }

    public void setAclName(String mName) {
        this.mName = mName;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public void setTelephone(String mTelephone) {
        this.mTelephone = mTelephone;
    }
}
