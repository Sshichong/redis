package com.example.demo.constant;



/**
 * Created by shichong on 2018/7/12.
 */
public enum LogEnum {

    ////////////// CURRENCY ////////////////
    UNKNOW("001","未知"),

    ////////////// MODULE ////////////////
    ILLNESS_INFO("101","未知"),
    USER_INFO("102","未知"),
    MEDICINE_INFO("103","未知"),
    PRESCRIPTION_INFO("104","未知"),
    DOCTOR_INFO("105","未知"),


    ////////////// OPERATE ////////////////
    SAVE("201","未知"),
    DELETE("202","未知"),
    MODIFITY("203","未知"),
    SELECT("204","未知");


    private String code;
    private String desc;

    LogEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String code(){
        return code;
    }

    public String desc(){
        return desc;
    }


}
