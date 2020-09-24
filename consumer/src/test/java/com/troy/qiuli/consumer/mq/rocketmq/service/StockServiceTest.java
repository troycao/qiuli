package com.troy.qiuli.consumer.mq.rocketmq.service;

import com.troy.qiuli.dao.entity.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caoqiang
 * @date 2020-09-22 14:39
 * @desc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockServiceTest {

    @Autowired
    StockService stockService;

    @Test
    public void updateStockTest(){
        Stock stock = new Stock();
        stock.setGoodsId(1L);
        stockService.updateStock(stock);
    }
}
