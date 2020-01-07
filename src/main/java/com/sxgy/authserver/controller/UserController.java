package com.sxgy.authserver.controller;

import java.util.List;

import com.sxgy.authserver.model.domain.User;
import com.sxgy.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    //依赖注入
    @Autowired
    UserService userService;
    @RequestMapping(value = "getAll")//method 不写的话，默认GET、POST都支持，根据前端方式自动适应
/*@GetMapping("getAll")//@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
@PostMapping("getAll")//@PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。*/
    @ResponseBody
    public List<User> getAll() {
//调用dao层
        List<User> all = userService.getAll();
        System.out.println(all);
        return all;
    }
    @RequestMapping("/login")
    public String login() {
        return "/login";
    }
    @RequestMapping("/login2")
    public String login2() {
        return "views/login";
    }
    @RequestMapping("/user")
    public String login3() {
        return "views/user/user";
    }
    @RequestMapping("/dept")
    public String login4() {
        return "views/dept/dept";
    }
}
