package com.sxgy.authserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAuthTest {

    @Autowired
    UserAuthService userAuthService;

    @Test
    public void testWechatMiniLogin() {
        userAuthService.wechatMiniLogin("opAkfwJ_jPDgv028c7HX4R1M_w7k", "+HfmuDXrh45PwiRUpDiLXg==");
    }
}
