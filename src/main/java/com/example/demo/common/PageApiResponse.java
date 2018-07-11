package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by shichong on 2018/6/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageApiResponse {
    private Integer total;
    private Integer page;
    private Integer size;


}
