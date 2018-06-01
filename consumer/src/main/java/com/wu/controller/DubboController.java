package com.wu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wu.dubbo.DubboUserService;
import com.wu.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by MJN on 2018/4/19.
 */
@Api(tags = "dubbo测试相关接口",description = "")
@Controller
@RequestMapping("/dubbo")
public class DubboController {

    private static Logger logger = LoggerFactory.getLogger("dubbo");
    @Reference
    private DubboUserService dubboUserService;

    @ApiOperation("用户信息查询接口")
    @RequestMapping(value = "queryByName",method = {RequestMethod.GET})
    public List<User> queryByName(@ApiParam(value = "用户名")@RequestParam String userName){
        return dubboUserService.queryUsers(userName);
    }
}
