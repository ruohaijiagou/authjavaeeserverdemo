package com.sxgy.authserver.service;

import java.util.List;

import com.sxgy.authserver.model.dao.UserMapper;
import com.sxgy.authserver.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserMapper {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
