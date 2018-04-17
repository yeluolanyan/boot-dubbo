package com.wu.service.impl;

import com.wu.dao.mapper.UserMapper;
import com.wu.model.User;
import com.wu.model.UserExample;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MJN on 2018/4/16.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser(){
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> queryUserByName(String username){
        UserExample example = new UserExample();
        example.or().andUserNameEqualTo(username);
        return userMapper.selectByExample(example);
    }
}
