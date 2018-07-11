package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by shichong on 2018/7/9.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Illness implements Serializable {
    private Integer illnessId;
    private String illnessName;
    private String illnessAdmin;
    private String illnessRemark;
    private String illnessState;

}
