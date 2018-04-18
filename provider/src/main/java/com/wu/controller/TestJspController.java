package com.wu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MJN on 2018/4/17.
 */
@Api(value = "返回jsp相关接口",description = "返回jsp相关接口 ")
@Controller
@RequestMapping("/test")
public class TestJspController {

    @ApiOperation("测试index接口")
    @RequestMapping(value = "/index")
    public ModelAndView  test(){
        ModelAndView mv =new ModelAndView("/index");
        mv.addObject("index","abbbbbbbbbbbbbbbb");
        return mv;
    }

    @RequestMapping(value = "/hello")
    public String  hello(HttpServletRequest request){
        request.setAttribute("hello","ppppppppppppppp");
        return "/hello";
    }
}
