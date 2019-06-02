package com.story.controller.login;

import com.story.common.utils.FastJsonUtil;
import com.story.common.utils.ResultBuilderUtil;
import com.story.pojo.dto.SearchUserRequest;
import com.story.pojo.po.UserPO;
import com.story.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping(value = {"/", "/login", "/login.html"})
    public String login() {
        return "login";
    }

    /**
     * 登陆界面 判断账号密码是否错误 以及加载菜单
     *
     * @param payload
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object doLogin(@RequestBody String payload) {
        SearchUserRequest requestDTO = FastJsonUtil.toBean(payload, SearchUserRequest.class);
        if (StringUtils.isEmpty(requestDTO.getLoginName()) || StringUtils.isEmpty(requestDTO.getPassword())) {
            return ResultBuilderUtil.buildError("用户密码不能为空");
        }

        UserPO result = userService.getUserInfo(requestDTO);
        if (Objects.isNull(result)) {
            return ResultBuilderUtil.buildError("用户不存在");
        }
        return ResultBuilderUtil.buildSuccess(result);

    }

}
