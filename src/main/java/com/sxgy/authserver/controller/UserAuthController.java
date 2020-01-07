package com.sxgy.authserver.controller;

import java.util.List;

import com.sxgy.authserver.model.domain.Token;
import com.sxgy.authserver.model.domain.UserAuth;
import com.sxgy.authserver.model.domain.UserInfo;
import com.sxgy.authserver.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserAuthController {
    //依赖注入
    @Autowired
    UserAuthService userAuthService;

    @RequestMapping("/wechat_mini_login")
    @ResponseBody
    public UserInfo wechatMiniLogin(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name= "code", required = true) String code
    ) {
        UserInfo userInfo = null;
        // 登录业务逻辑，返回token
        Token token = userAuthService.wechatMiniLogin(code);
        // 根据token，获取用户信息
        userInfo = userAuthService.getUserInfoByToken(token.getToken());
        // 将token设置到header中
        response.addHeader("token", token.getToken());
        // 返回用户信息
        return userInfo;
    }

}
