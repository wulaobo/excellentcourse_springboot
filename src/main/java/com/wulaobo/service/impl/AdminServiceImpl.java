package com.wulaobo.service.impl;

import com.wulaobo.bean.User;
import com.wulaobo.mapper.UserMapper;
import com.wulaobo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User adminLogin(String username, String password) {
        return userMapper.adminLogin(username,password);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public List<User> selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public boolean changeRoleByUser(User user) {
        return userMapper.changeRoleByUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean updateStateByUser(User user) {
        return userMapper.updateStateByUser(user);
    }

    @Override
    public boolean updateAdminPassword(User admin) {
        return userMapper.updateAdminPassword(admin);
    }
}
