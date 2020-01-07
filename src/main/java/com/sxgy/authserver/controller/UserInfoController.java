package com.sxgy.authserver.controller;

import com.sxgy.authserver.model.domain.UserInfo;
import com.sxgy.authserver.service.UserAuthService;
import com.sxgy.authserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserInfoController {

    //依赖注入
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/get_user_info")
    @ResponseBody
    public UserInfo getUserInfo(HttpServletRequest request, HttpServletResponse response){
        UserInfo userInfo = null;

        return userInfo;
    }
}
