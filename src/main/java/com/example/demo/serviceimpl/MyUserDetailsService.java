package com.example.demo.serviceimpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by shichong on 2018/8/27.
 * 配置用户认证逻辑
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("用户的用户名：{}",s);

        String password =passwordEncoder.encode("123456");
        logger.info("password:{}",password);

        //TODO 根据用户名，查找对应的密码，与权限
        //封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User(s,password,AuthorityUtils.commaSeparatedStringToAuthorityList("admin" ));
        return user;
    }
}
