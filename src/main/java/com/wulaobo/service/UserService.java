package com.wulaobo.service;

import com.wulaobo.bean.User;

public interface UserService {
    User login(String username, String password);

    void save(User user);

    User getUserByUserName(String username);

    void updatePasswordByUserName(String md5Pwd, String userName);
}
