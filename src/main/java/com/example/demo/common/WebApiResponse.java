package com.example.demo.common;

import com.example.demo.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 通用API返回
 * Created by shichong on 2018/6/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WebApiResponse<T> {
    private String code;
    private String msg;
    private T      data;
    private PageApiResponse page;

    public WebApiResponse(String code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static <T> WebApiResponse<T> success(T data){
        return new WebApiResponse<T>(Constant.WebApiResDict.SUCCESS_CODE,Constant.HttpMsgDict.SUCCESS_MSG,data);
    }

    public static <T> WebApiResponse<T> error(String msg){
        return new WebApiResponse<T>(Constant.WebApiResDict.ERROR_CODE,msg,null);
    }

    public static <T> WebApiResponse<T> error(String code,String msg){
        return new WebApiResponse<T>(code,msg,null);
    }



}
