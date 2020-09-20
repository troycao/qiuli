package com.troy.qiuli.consumer.mq.rocketmq.service.impl;

import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.dao.mapper.GoodsOrderMapper;
import com.troy.qiuli.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caoqiang
 * @date 2020-09-20 17:47
 * @desc
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    GoodsOrderMapper goodsOrderMapper;

    @Override
    public int saveGoodsOrder(GoodsOrder goodsOrder) {
        return goodsOrderMapper.insert(goodsOrder);
    }
}
