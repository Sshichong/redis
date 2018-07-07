package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shichong on 2018/7/7.
 */
@RestController
@RequestMapping("/")
public class pageController {

    @RequestMapping(value = "/{pageName}",method = RequestMethod.GET)
    public String toPage(@PathVariable("pageName")String pageName){
        return pageName;
    }
}
