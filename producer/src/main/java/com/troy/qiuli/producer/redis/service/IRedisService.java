package com.troy.qiuli.producer.redis.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author caoqiang
 * @date 2020-09-03 11:19
 * @desc
 */
public interface IRedisService {

    void save(Object object);

    Map<?, ?> list(String key);

    Object getValue(String key);

    void delete(String key);
}
