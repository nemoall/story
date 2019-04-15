package com.story.controller;

import com.story.pojo.po.UserPO;
import com.story.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class RandomNameController {

    @Autowired
    private UserService userService;

    @RequestMapping("/random")
    public String home() {
        List<UserPO> userPOS = userService.listUserInfos();
        if(CollectionUtils.isEmpty(userPOS)){
            userService.resetArchive();
            userPOS = userService.listUserInfos();
        }
        UserPO result  = userPOS.get((int)(Math.random() * (userPOS.size() - 1 + 1)));
        userService.updateUserArchive(result.getUserUid());
        return result.getUserName();
    }

}
