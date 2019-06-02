package com.story.service;

import com.story.pojo.dto.SearchUserRequest;
import com.story.pojo.po.UserPO;

import java.util.List;

/**
 * created by LL on 2019/4/13
 */
public interface UserService {
    UserPO getUserInfo(SearchUserRequest request);
}
