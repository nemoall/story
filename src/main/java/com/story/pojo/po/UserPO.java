package com.story.pojo.po;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * created by LL on 2019/4/13
 */
public class UserPO {
    private String userUid;
    private String userName;
    private Integer archive;

    public Integer getArchive() {
        return archive;
    }

    public void setArchive(Integer archive) {
        this.archive = archive;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
