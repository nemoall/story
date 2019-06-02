package com.story.mapper.user;

import com.story.pojo.dto.SearchUserRequest;
import com.story.pojo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by LL on 2019/4/13
 */
@Mapper
public interface UserMapper {
    UserPO getUserInfo(SearchUserRequest request);

}
