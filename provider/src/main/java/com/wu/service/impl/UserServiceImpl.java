package com.wu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wu.dao.mapper.UserMapper;
import com.wu.model.User;
import com.wu.model.UserExample;
import com.wu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger("user");
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser(){
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> queryUserByName(String username){
        logger.info("查询用户 userName={}", username);
        UserExample example = new UserExample();
        example.or().andUserNameEqualTo(username);
        return userMapper.selectByExample(example);
    }

    //事务配置Propagation 事务传播行为，isolation 事务隔离级别设置，timeout 事务超时时间设置,rollbackFor 导致事务回滚的异常类数组
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Integer saveUser(User user){
        logger.info(" 添加新用户 user = [{}]----userName={}", JSONObject.toJSON(user),user.getUserName());
        logger.error(" 失败了error--------------------");
        return userMapper.insert(user);
    }
}
