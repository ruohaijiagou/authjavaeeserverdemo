package com.sxgy.authserver.service;

import com.sxgy.authserver.model.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

}
