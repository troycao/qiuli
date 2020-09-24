package com.troy.qiuli.consumer.mq.rocketmq.service;

import com.troy.qiuli.dao.entity.Stock;

/**
 * @author caoqiang
 * @date 2020-09-21 14:35
 * @desc
 */
public interface StockService {

    int updateStock(Stock stock);
}
