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

    @RequestMapping(value = "getAll")//method 不写的话，默认GET、POST都支持，根据前端方式自动适应
/*@GetMapping("getAll")//@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
@PostMapping("getAll")//@PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。*/
    @ResponseBody
    public List<UserAuth> getAll() {

        List<UserAuth> all = userAuthService.getAll();
        System.out.println(all);
        return all;
    }
    @RequestMapping("/wechat_login")
    @ResponseBody
    public UserInfo wechatLogin(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name= "openid", required = true) String openid,
                                @RequestParam(name = "sessionKey", required = true) String sessionKey
    ) {
        UserInfo userInfo = null;
        // 登录业务逻辑，返回token
        Token token = userAuthService.wechatMiniLogin(openid, sessionKey);
        // 根据token，获取用户信息
        userInfo = userAuthService.getUserInfoByToken(token.getToken());
        // 将token注入cookie
        Cookie cookie = new Cookie("token", token.getToken());
        // 存活时间60分钟
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        cookie.setDomain("sxgy.com");
        response.addCookie(cookie);
        return userInfo;
    }

    @RequestMapping("/user")
    public String login3() {
        return "views/user/user";
    }

    @RequestMapping("/dept")
    public String dept() {
        return "views/dept/dept";
    }
}
