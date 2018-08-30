package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.WebApiResponse;
import com.example.demo.model.User;
import com.example.demo.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shichong on 2018/7/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 添加到数据库和redis缓存
     * @param user
     * @return
     */
    @PostMapping
    @ResponseBody
    @Transactional
    public Result<?> saveUserInfo(@RequestBody User user){
        //存入数据库
        userService.saveUserInfo(user);

        //从数据库中找出完整的包含id的此条记录，并存入redis
        User u= userService.findUserByName(user.getUserNickname());
        userService.put(String.valueOf(u.getUserId()),u,-1);

       return Result.success(null);
    }

    @GetMapping
    public String getUsers() {
        return "Hello Spring Security";
    }
}
