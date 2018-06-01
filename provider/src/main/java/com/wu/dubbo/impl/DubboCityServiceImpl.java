package com.wu.dubbo.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.wu.dubbo.DubboCityService;
import com.wu.model.User;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service
public class DubboCityServiceImpl implements DubboCityService {
    @Autowired
    private UserService userService;

    public String findCityByName(String cityName) {
        User user = new User();
        userService.saveUser(user);
        return "--------------";
    }
}
