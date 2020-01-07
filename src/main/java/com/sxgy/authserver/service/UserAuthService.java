package com.sxgy.authserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sxgy.authserver.common.util.IdWorker;
import com.sxgy.authserver.model.dao.UserAuthMapper;
import com.sxgy.authserver.model.dao.UserInfoMapper;
import com.sxgy.authserver.model.domain.Token;
import com.sxgy.authserver.model.domain.UserAuth;
import com.sxgy.authserver.model.domain.UserInfo;
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
    public Token wechatMiniLogin(String openid, String sessionKey) {
        UserAuth userAuth;
        UserInfo userInfo = null;
        userAuth = userAuthMapper.getUserByWechatMiniOpenid(openid);
        if (userAuth == null) {
            userAuth = new UserAuth();
            userAuth.setId(idWorker.nextId());
            userAuth.setWechatMiniOpenid(openid);
            userAuth.setWechatMiniSessionKey(sessionKey);
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
