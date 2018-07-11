package com.example.demo.service;

import com.example.demo.model.Illness;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by shichong on 2018/7/9.
 */
public interface IllnessService  {
    List<Illness> getList(Integer pageNo, Integer pageSize);

    List<Illness> findInfoIllness();

    Page<Illness> getListAll(Integer pageNo, Integer pageSize);

    boolean isExistByIllnessName(String illnessName);

    void saveIllnessInfo(Illness illness);

    void updateIllnessInfo(Illness illness);

    void deleteIllnessInfos(List<Integer> ids);

    void deleteIllnessInfo(Integer id);

    Illness findIllnessById(Integer illnessId);
}
