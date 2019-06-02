package com.story.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SearchUserRequest {
    @JSONField(name = "login_name")
    private String loginName;
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
