package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by shichong on 2018/7/11.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String userNickname;
    private String userEmail;
    private String userpassword;
    private String userHeadpic;
    private String userPower;

    public User(String userNickname, String userpassword, String userPower) {
        this.userNickname = userNickname;
        this.userpassword = userpassword;
        this.userPower = userPower;
    }
}
