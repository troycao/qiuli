package com.troy.qiuli.producer.mq.rocketmq.service;

import com.troy.qiuli.bo.GoodsOrderBO;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author caoqiang
 * @date 2020-08-31 14:43
 * @desc
 */
public interface RocketMqService {

    void createGoodsOrder(GoodsOrderBO goodsOrderBO) throws Exception;

}
