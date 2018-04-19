package com.wu.controller;

import com.wu.common.redis.RedisClient;
import com.wu.model.User;
import com.wu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MJN on 2018/4/16.
 */
@Api(value = "Redis相关接口",description = "redis 测试接口")
@RestController //将@Controller 与@ResponseBody 进行了合并成一个新的注解
@RequestMapping("redis")
public class RedisController {
    private static Logger logger = LoggerFactory.getLogger("redis");
    @Autowired
    private RedisClient redisClient;


    @ApiOperation("putRedis接口")
    @RequestMapping(value = "putRedis",method = {RequestMethod.GET})
    public void putRedis(@RequestParam String key,@RequestParam String value){
         redisClient.putString(key,value);
    }


    @ApiOperation("用户新增接口")
    @RequestMapping(value = "getRedis",method = RequestMethod.POST)
    public Object getRedis(@RequestParam String key){
        return redisClient.getString(key);
    }
}
