package com.wu.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wu.dubbo.DubboUserService;
import com.wu.model.User;
import com.wu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by MJN on 2018/4/16.
 */
//注意该注解是com.alibaba.dubbo.config.annotation.Service
@Service(version = "1.0.0")
public class DubboUserServiceImpl implements DubboUserService {
    private static Logger logger = LoggerFactory.getLogger("user");
    //dubbo层调用service层的事务服务
    @Autowired
    private UserService userService;


    @Override
    public void saveUser(User user){
        userService.saveUser(user);
    }

    @Override
    public List<User> queryUsers(String name){
        return userService.queryUserByName(name);
    }
}
