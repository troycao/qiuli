package com.troy.qiuli.producer.mq.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.troy.qiuli.bo.GoodsOrderBO;
import com.troy.qiuli.common.enums.MQEnum;
import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.producer.mq.rocketmq.product.RocketMQProducer;
import com.troy.qiuli.producer.mq.rocketmq.service.RocketMqService;
import com.troy.qiuli.service.OrderService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author caoqiang
 * @date 2020-08-31 14:13
 * @desc
 */

@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Resource
    private RocketMQProducer rocketMQProducer;

    @Autowired
    private OrderService orderService;

    @Override
    public SendResult createGoodsOrder(GoodsOrderBO goodsOrderBO) throws Exception {
        if (ObjectUtils.isEmpty(goodsOrderBO)) {
            throw new Exception("test");
        }

        GoodsOrder goodsOrder = new GoodsOrder();
        BeanUtils.copyProperties(goodsOrderBO, goodsOrder);
        int i = orderService.saveGoodsOrder(goodsOrder);
        if (i == 1){
            System.out.println("保存订单成功,订单id = " + goodsOrder.getId());
        }

        // 可以不使用Config中的Group
        SendResult sendResult = null;
        try {
            sendResult = rocketMQProducer.send(MQEnum.Topic.TROY_QIULI_ORDER_SYNC,
                    MQEnum.Tags.TROY_QIULI_ORDER_TO_CONSUMER,
                    JSON.toJSONString(goodsOrder),
                    "order_key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }

}
