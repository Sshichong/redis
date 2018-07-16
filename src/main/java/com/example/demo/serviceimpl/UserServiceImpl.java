package com.example.demo.serviceimpl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by shichong on 2018/7/11.
 */
@Service
public class UserServiceImpl extends IRedisService<User> {
    private static final String REDIS_KEY = "User";

    @Autowired
    UserMapper userMapper;

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }

    public void saveUserInfo(User user) {
         userMapper.saveUserInfo(user);
    }

    public User findUserByName(String userNickname) {
        return userMapper.findUserByName(userNickname);
    }
}
