package com.wulaobo.service.impl;

import com.wulaobo.bean.User;
import com.wulaobo.mapper.UserMapper;
import com.wulaobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public void updatePasswordByUserName(String md5Pwd, String userName) {
        userMapper.updatePasswordByUserName(md5Pwd,userName);
    }
}
