package com.wu.service;

import com.wu.model.User;

import java.util.List;

/**
 * Created by MJN on 2018/4/16.
 */
public interface UserService {
    List<User> queryAllUser();

    List<User> queryUserByName(String username);
}
