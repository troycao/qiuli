package com.troy.qiuli.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author caoqiang
 * @date 2020-08-31 10:13
 * @desc
 */
@SpringBootApplication(scanBasePackages = "com.troy.qiuli")
@MapperScan("com.troy.qiuli.dao")
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}