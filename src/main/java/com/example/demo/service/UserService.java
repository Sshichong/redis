package com.example.demo.service;

import com.example.demo.model.User;
import javafx.scene.effect.SepiaTone;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by shichong on 2018/7/11.
 */
public abstract class UserService<T> {

    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    @Resource
    protected HashOperations<String,String,T> hashOperations;

    /**
     * 存入redis中的key
     * @return
     */
    protected abstract String getRedisKey();

    /**
     * 添加
     * @param key
     * @param model
     * @param expire
     */
    public void put(String key,T model,long expire){
        hashOperations.put(getRedisKey(),key,model);
        if(expire!=-1){
            redisTemplate.expire(getRedisKey(),expire, TimeUnit.SECONDS);
        }
    }


    /**
     * 删除
     * @param key
     */
    public void remove(String key){
        hashOperations.delete(getRedisKey(),key);
    }

    /**
     * 查询
     * @param key
     * @return
     */
    public T get(String key){
        return hashOperations.get(getRedisKey(),key);
    }

    /**
     * 获取当前redis库下所有对象
     * @return
     */
    public List<T> getAll(){
        return hashOperations.values(getRedisKey());
    }


    /**
     * 查询查询当前redis库下所有key
     * @return
     */
    public Set<String> getKey(){
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 判断key是否存在redis中
     * @param key
     * @return
     */
    public boolean isKeyExists(String key){
        return hashOperations.hasKey(getRedisKey(),key);
    }

    /**
     * 查询当前key下缓存数量
     * @return
     */
    public long count(){
        return hashOperations.size(getRedisKey());
    }

    public void empty(){
        Set<String> set =hashOperations.keys(getRedisKey());
        set.stream().forEach(key ->hashOperations.delete(getRedisKey(),key));
    }



}
