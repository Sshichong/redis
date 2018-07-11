package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by shichong on 2018/7/11.
 */
@Mapper
public interface UserMapper {


    @Insert("insert into user" +
            "(User_Nickname,User_Email,User_Password,User_HeadPic) values" +
            "(#{userNickname},#{userEmail},#{userpassword},#{userHeadpic})")
    void saveUserInfo(User user);

    @Select("select * from user " +
            "where User_Nickname=#{userNickname}")
    @Results({
            @Result(property = "userId", column = "User_ID"),
            @Result(property = "userNickname", column = "User_Nickname"),
            @Result(property = "userEmail", column = "User_Email"),
            @Result(property = "userpassword", column = "User_Password"),
            @Result(property = "userHeadpic", column = "User_HeadPic"),
    })
    User findUserByName(String userNickname);
}
