package com.example.demo.serviceimpl;

import com.example.demo.mapper.IllnessMapper;
import com.example.demo.model.Illness;
import com.example.demo.service.IRedisService;
import com.example.demo.service.IllnessService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shichong on 2018/7/9.
 */
@Service
public class IllnessServiceImpl implements IllnessService {


    @Autowired
    IllnessMapper illnessMapper;


    @Override
    public Illness findIllnessById(Integer illnessId) {
        Illness illness= illnessMapper.findIllnessById(illnessId);
        return illness;
    }

    @Override
    public void deleteIllnessInfo(Integer id) {
        illnessMapper.deleteIllnessInfo(id);
    }

    @Override
    public void deleteIllnessInfos(List<Integer> ids) {
        illnessMapper.deleteIllnessInfos(ids);
    }

    @Override
    public void updateIllnessInfo(Illness illness) {
        illnessMapper.updateIllnessInfo(illness);
    }

    @Override
    public boolean isExistByIllnessName(String illnessName) {
        return illnessMapper.isExistByIllnessName(illnessName)==1?true:false;

    }

    @Override
    public void saveIllnessInfo(Illness illness) {
        illnessMapper.saveIllnessInfo(illness);
    }

    @Override
    public Page<Illness> getListAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        Page<Illness> page=(Page<Illness>) illnessMapper.findIllnessInfos();
        return page;
    }

    @Override
    public List<Illness> getList(Integer pageNo, Integer pageSize){
        //
        PageHelper.startPage(pageNo,pageSize);
        //
        List<Illness> list =illnessMapper.findIllnessInfos();
        return list;
    }

    @Override
    public List<Illness> findInfoIllness() {
        return illnessMapper.findIllnessInfos();
    }

}
