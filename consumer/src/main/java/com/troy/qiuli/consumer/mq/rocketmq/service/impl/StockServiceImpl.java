package com.troy.qiuli.consumer.mq.rocketmq.service.impl;

import com.troy.qiuli.common.constants.Constants;
import com.troy.qiuli.common.enums.CodeEnum;
import com.troy.qiuli.common.exception.QiuLiException;
import com.troy.qiuli.common.redis.RedisUtil;
import com.troy.qiuli.consumer.mq.rocketmq.service.StockService;
import com.troy.qiuli.dao.entity.Stock;
import com.troy.qiuli.dao.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author caoqiang
 * @date 2020-09-20 17:47
 * @desc
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper stockMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStock(Stock stock) {
        Stock stockFromDb = stockMapper.selectForUpdate(stock.getGoodsId());
        if (stockFromDb.getNum() <= 0) {
            redisUtil.set(Constants.OUT_OF_STOCK + stock.getGoodsId(), "0");
            throw new QiuLiException(CodeEnum.STOCK_IS_ZERO);
        }
        return stockMapper.updateBySelective(stock);
    }
}
