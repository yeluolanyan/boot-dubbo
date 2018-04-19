package com.wu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MJN on 2018/4/17.
 */
@Controller
@RequestMapping("/test")
public class TestJspController {
    private static Logger logger = LoggerFactory.getLogger("test");
    @RequestMapping(value = "/index")
    public ModelAndView  test(){
        ModelAndView mv =new ModelAndView("/index");
        logger.info("info ---------------------");
        logger.error("error ---------------------");
        mv.addObject("index","abbbbbbbbbbbbbbbb");
        return mv;
    }

    @RequestMapping(value = "/hello")
    public String  hello(HttpServletRequest request){
        request.setAttribute("hello","ppppppppppppppp");
        return "/hello";
    }
}
