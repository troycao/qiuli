package com.troy.qiuli.consumer.mq.rocketmq.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author caoqiang
 * @date 2020-09-02 11:43
 * @desc
 */
@Data
@Service
public class ParamConfigService {

    @Value("${rocketmq.topic}")
    public String rocketTopic ;
    @Value("${rocketmq.tag}")
    public String rocketTag ;
}