package com.sxgy.authserver.model.domain;

public class UserAuth {
    private Long id;
    private String phone;
    private String password;
    private String wechatMiniOpenid;
    private String wechatMiniSessionKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWechatMiniOpenid() {
        return wechatMiniOpenid;
    }

    public void setWechatMiniOpenid(String wechatMiniOpenid) {
        this.wechatMiniOpenid = wechatMiniOpenid;
    }

    public String getWechatMiniSessionKey() {
        return wechatMiniSessionKey;
    }

    public void setWechatMiniSessionKey(String wechatMiniSessionKey) {
        this.wechatMiniSessionKey = wechatMiniSessionKey;
    }
}
