package com.troy.qiuli.consumer.mq.rocketmq.service;

import com.troy.qiuli.dao.entity.GoodsOrder;

/**
 * @author caoqiang
 * @date 2020-09-20 17:46
 * @desc
 */
public interface OrderService {

    int save(GoodsOrder goodsOrder);
}
