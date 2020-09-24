package com.troy.qiuli.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author caoqiang
 * @date 2020-08-31 10:14
 * @desc
 */
@SpringBootApplication(scanBasePackages = "com.troy.qiuli")
@MapperScan("com.troy.qiuli.dao.mapper")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
