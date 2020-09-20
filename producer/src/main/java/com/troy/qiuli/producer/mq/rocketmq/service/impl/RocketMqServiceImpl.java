package com.troy.qiuli.producer.mq.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.troy.qiuli.bo.GoodsOrderBO;
import com.troy.qiuli.common.enums.MqEnum;
import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.producer.mq.rocketmq.product.RocketMqProducer;
import com.troy.qiuli.producer.mq.rocketmq.service.RocketMqService;
import com.troy.qiuli.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author caoqiang
 * @date 2020-08-31 14:13
 * @desc
 */

@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Resource
    private RocketMqProducer rocketMqProducer;

    @Autowired
    private OrderService orderService;

    @Override
    public void createGoodsOrder(GoodsOrderBO goodsOrderBO) throws Exception {
        GoodsOrder goodsOrder = new GoodsOrder();
        BeanUtils.copyProperties(goodsOrderBO, goodsOrder);
        int i = orderService.saveGoodsOrder(goodsOrder);
        if (i == 1){
            System.out.println("保存订单成功,订单id = " + goodsOrder.getId());
        }

        try {
            rocketMqProducer.send(MqEnum.Topic.TROY_QIULI_ORDER_SYNC_TOPIC,
                    MqEnum.Tags.TROY_QIULI_ORDER_TO_CONSUMER_TAG,
                    JSON.toJSONString(goodsOrder),
                    "order_key");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
