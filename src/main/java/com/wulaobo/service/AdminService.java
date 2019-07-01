package com.wulaobo.service;

import com.wulaobo.bean.User;

import java.util.List;

public interface AdminService {
    User adminLogin(String username, String password);

    List<User> getAllUser();

    List<User> selectByName(String username);

    boolean deleteUserById(Integer id);

    boolean changeRoleByUser(User user);

    User getUserById(Integer id);

    boolean updateStateByUser(User user);

    boolean updateAdminPassword(User admin);
}
