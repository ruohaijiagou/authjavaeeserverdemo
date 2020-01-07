package com.sxgy.authserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.sxgy.authserver.common.util.HttpClientUtil;
import com.sxgy.authserver.common.util.IdWorker;
import com.sxgy.authserver.model.dao.UserAuthMapper;
import com.sxgy.authserver.model.dao.UserInfoMapper;
import com.sxgy.authserver.model.domain.Token;
import com.sxgy.authserver.model.domain.UserAuth;
import com.sxgy.authserver.model.domain.UserInfo;
import com.sxgy.authserver.model.vo.WechatMiniCode2Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    Map<String, UserInfo> tokenStorage = new HashMap<>();
    IdWorker idWorker = new IdWorker(1,1,1);

    public List<UserAuth> getAll() {
        return userAuthMapper.getAll();
    }

    // 开启事务
    @Transactional(propagation = Propagation.REQUIRED)
    public Token wechatMiniLogin(String code) {
        UserAuth userAuth;
        UserInfo userInfo = null;

        // 请求微信，获取openid和sessionKey
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("appid", "wxdf4d19ce9f61bb6d");
        queryParam.put("secret", "0842e4f4c59b7057c65600017e2c283c");
        queryParam.put("grant_type", "authorization_code");
        queryParam.put("js_code", code);

        String wechatResult = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session", queryParam);
        WechatMiniCode2Session wechatMiniCode2Session = JSON.parseObject(wechatResult, WechatMiniCode2Session.class);
        System.out.println(wechatResult);
        System.out.println(wechatMiniCode2Session);
        userAuth = userAuthMapper.getUserByWechatMiniOpenid(wechatMiniCode2Session.getOpenid());
        if (userAuth == null) {
            userAuth = new UserAuth();
            userAuth.setId(idWorker.nextId());
            userAuth.setWechatMiniOpenid(wechatMiniCode2Session.getOpenid());
            userAuth.setWechatMiniSessionKey(wechatMiniCode2Session.getSessionKey());
            userAuthMapper.saveUserAuth(userAuth);

            userInfo = new UserInfo();
            userInfo.setId(idWorker.nextId());
            userInfo.setUserId(userAuth.getId());
            userInfoMapper.saveUserInfo(userInfo);

        }else {
            userInfo = userInfoMapper.getUserByUserID(userAuth.getId());
        }
        // 生成token
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        tokenStorage.put(token.getToken(), userInfo);

        return token;
    }

    public UserInfo getUserInfoByToken(String token){
        return tokenStorage.get(token);
    }
}
