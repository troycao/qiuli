package com.troy.qiuli.producer.redis.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.troy.qiuli.bo.UserBO;
import com.troy.qiuli.dao.entity.User;
import com.troy.qiuli.producer.redis.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author caoqiang
 * @date 2020-09-03 11:20
 * @desc
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Override
    public void save(Object object) {
        Long userId = IdUtil.createSnowflake(1, 1).nextId();
        UserBO user = new UserBO(userId, "东方不败", "dfbb");
        String key = "user_" + userId;

        redisTemplate.opsForValue().append(key, JSON.toJSONString(user));
    }



    @Override
    public Map<?, ?> list(String key) {
        return null;
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        Boolean delete = redisTemplate.delete(key);
        if (delete){
            System.out.println("删除成功");
        }

    }
}
