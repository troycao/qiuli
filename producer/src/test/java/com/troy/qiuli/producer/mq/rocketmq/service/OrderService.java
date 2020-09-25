package com.troy.qiuli.producer.mq.rocketmq.service;

import cn.hutool.core.util.IdUtil;
import com.troy.qiuli.common.Result;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;
import com.troy.qiuli.producer.mq.rocketmq.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author caoqiang
 * @date 2020-09-24 15:09
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderService {

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void saveOrderTest(){
        for (int i = 0; i < 10; i++) {
            GoodsOrderRequestVo goodsOrderRequestVo = new GoodsOrderRequestVo(
                    IdUtil.createSnowflake(1,1).nextId(),
                    "大衣",
                    (short) 1,
                    10000000000000000L,
                    new BigDecimal(128.8).setScale(2),
                    "1",
                    "张三"
                    ,1L);
            Result<?> goodsOrder = orderService.createGoodsOrder(goodsOrderRequestVo);
            System.out.println(goodsOrder);
        }
    }
}
