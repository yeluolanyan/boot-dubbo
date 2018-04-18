package com.wu.controller;

import com.wu.model.User;
import com.wu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by MJN on 2018/4/16.
 */
@Api(value = "用户相关接口",description = "用户查询新增相关rest接口")
@RestController //将@Controller 与@ResponseBody 进行了合并成一个新的注解
@RequestMapping("user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger("user");
    @Autowired
    private UserService userService;


    @ApiOperation("用户信息查询接口")
    @RequestMapping(value = "queryByName",method = {RequestMethod.GET,RequestMethod.POST})
    public List<User> queryByName(@ApiParam(value = "用户名")@RequestParam String userName){
        return userService.queryUserByName(userName);
    }


    @ApiOperation("用户新增接口")
    @RequestMapping(value = "saveUser",method = RequestMethod.POST)
    public Object queryByName(@RequestBody User user){
        return userService.saveUser(user);
    }
}
