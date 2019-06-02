package com.story.controller;

import com.story.pojo.po.UserPO;
import com.story.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Controller
public class RandomNameController {

    @Autowired
    private UserService userService;

    @GetMapping("/index.html")
    public String newindex() {
        System.out.println("111");
        return "index";
    }

    @GetMapping("/memberIndex.html")
    public String memberIndex() {
        System.out.println("111");
        return "memberIndex";
    }

}
