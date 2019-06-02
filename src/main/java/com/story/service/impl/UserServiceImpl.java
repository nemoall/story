package com.story.service.impl;

import com.story.mapper.user.UserMapper;
import com.story.pojo.dto.SearchUserRequest;
import com.story.pojo.po.UserPO;
import com.story.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by LL on 2019/4/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserPO getUserInfo(SearchUserRequest request) {
        return userMapper.getUserInfo(request);
    }
}
