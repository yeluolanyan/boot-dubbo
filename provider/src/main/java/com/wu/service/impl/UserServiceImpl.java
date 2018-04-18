package com.wu.service.impl;

import com.wu.dao.mapper.UserMapper;
import com.wu.model.User;
import com.wu.model.UserExample;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    //事务配置Propagation 事务传播行为，isolation 事务隔离级别设置，timeout 事务超时时间设置,rollbackFor 导致事务回滚的异常类数组
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Integer saveUser(User user){
        return userMapper.insert(user);
    }
}
