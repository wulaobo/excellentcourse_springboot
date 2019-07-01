package com.wulaobo.mapper;

import com.wulaobo.bean.User;

public interface AdminMapper {
    User adminLogin(String username, String password);
}
