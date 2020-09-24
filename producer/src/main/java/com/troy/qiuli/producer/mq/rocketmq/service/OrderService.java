package com.troy.qiuli.producer.mq.rocketmq.service;

import com.troy.qiuli.common.Result;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;

/**
 * @author caoqiang
 * @date 2020-09-23 17:18
 * @desc
 */
public interface OrderService {

    Result<?> createGoodsOrder(GoodsOrderRequestVo goodsOrderVo);

}
