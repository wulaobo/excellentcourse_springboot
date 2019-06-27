package com.wulaobo.mapper;

import com.wulaobo.bean.User;

public interface UserMapper {

    User login(String username, String password);

    void save(User user);

    User getUserByUserName(String username);

    void updatePasswordByUserName(String md5Pwd, String userName);
}
