package com.story.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * created by LL on 2019/4/13
 */
public class UserPO {
    @JSONField(name = "user_uid")
    private String userUid;
    @JSONField(name = "login_name")
    private String loginName;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
