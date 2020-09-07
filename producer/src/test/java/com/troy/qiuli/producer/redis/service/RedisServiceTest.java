package com.troy.qiuli.producer.redis.service;

import com.troy.qiuli.producer.redis.service.impl.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caoqiang
 * @date 2020-09-03 14:44
 * @desc
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    RedisServiceImpl redisService;

    @Test
    public void saveTest(){
        for (int i = 0; i < 100; i++) {
            redisService.save(null);
        }
    }

    @Test
    public void deleteTest(){
        for (int i = 0; i < 100; i++) {
            redisService.delete("user_dfbb");
        }
    }

    @Test
    public void getValueTest(){
        System.out.println(redisService.getValue("user_1301415596734418944"));
    }
}
