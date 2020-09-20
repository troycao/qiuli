package com.troy.qiuli.producer.mq.rocketmq.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.troy.qiuli.bo.GoodsOrderBO;
import com.troy.qiuli.common.Result;
import com.troy.qiuli.producer.mq.rocketmq.service.RocketMqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author caoqiang
 * @date 2020-08-31 15:00
 * @desc
 */
@RestController
public class SendController {

    @Autowired
    RocketMqService rocketMqService;

    @RequestMapping("/sendOrder")
    public Result<?> sendOrder (){
        SendResult sendResult = null;
        try {
            for (int i = 0; i < 1; i++) {
                GoodsOrderBO goodsOrderBO;
                goodsOrderBO = new GoodsOrderBO(
                        IdUtil.createSnowflake(1,1).nextId(),
                        "大衣",
                        (short) 5,
                        10000000000000000L,
                        new BigDecimal(128.8),
                        "1",
                        DateUtil.date(),
                        DateUtil.date(),
                        "张三"
                        ,1L);

                rocketMqService.createGoodsOrder(goodsOrderBO) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
