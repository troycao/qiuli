package com.troy.qiuli.common.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caoqiang
 * @date 2020-09-23 17:04
 * @desc
 */
//@Configuration
public class RedissonConfig {

    @Value("spring.redis.host")
    private String redisAdd;

    @Value("spring.redis.prot")
    private String redisPort;

    @Value("spring.redis.database")
    private String redisDb;

    @Bean
    public RedissonClient config(){
        Config config = new Config();
//        System.out.println("=================" + redisDb);
        config.useSingleServer().setAddress(new StringBuffer(redisAdd).append(":").append(redisPort).toString()).setDatabase(Integer.valueOf(redisDb));
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
