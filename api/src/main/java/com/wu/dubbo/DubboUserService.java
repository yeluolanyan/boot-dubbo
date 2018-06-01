package com.wu.dubbo;

import com.wu.model.User;

import java.util.List;

/**
 * Created by MJN on 2018/4/16.
 */
public interface DubboUserService {

    void saveUser(User user);

    List<User> queryUsers(String name);
}
