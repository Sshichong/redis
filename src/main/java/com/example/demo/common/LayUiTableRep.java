package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by shichong on 2018/6/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayUiTableRep<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public static <T> LayUiTableRep<T> success(Integer count,List<T> data){
        return new LayUiTableRep<T>(0,"success",count,data);
    }

    public static <T> LayUiTableRep<T> error(){
        return new LayUiTableRep<T>(1,"error",null,null);
    }

}
