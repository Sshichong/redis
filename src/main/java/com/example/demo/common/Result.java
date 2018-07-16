package com.example.demo.common;

import com.example.demo.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by shichong on 2018/7/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String code;
    private String message;
    private T      data;

    public static <T> Result<T> success(String msg,T data){
        return new Result<T>(Constant.UserDict.SUCCESS_CODE,msg,data);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(Constant.UserDict.SUCCESS_CODE,Constant.UserDict.SUCCESS_MSG,data);
    }

    public static <T> Result<T> error(){
        return new Result<T>(Constant.UserDict.ERROR_CODE,Constant.UserDict.ERROR_MSG,null);
    }

    public static <T> Result<T> error(String msg){
        return new Result<T>(Constant.UserDict.ERROR_CODE,msg,null);
    }



}
