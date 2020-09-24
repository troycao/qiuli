package com.troy.qiuli.producer.mq.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.troy.qiuli.common.Result;
import com.troy.qiuli.common.constants.RedisKeys;
import com.troy.qiuli.common.enums.MqEnum;
import com.troy.qiuli.common.redis.RedisUtil;
import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.producer.mq.rocketmq.model.convert.GoodsOrderConvert;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;
import com.troy.qiuli.producer.mq.rocketmq.product.RocketMqProducer;
import com.troy.qiuli.producer.mq.rocketmq.service.OrderService;
import com.troy.qiuli.service.OrderBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author caoqiang
 * @date 2020-09-23 17:19
 * @desc
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RocketMqProducer rocketMqProducer;

    @Autowired
    private OrderBizService orderService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result<?> createGoodsOrder(GoodsOrderRequestVo goodsOrderVo) {

        GoodsOrder goodsOrder = GoodsOrderConvert.goodsOrderVo2Bo(goodsOrderVo);
        Object outStock = redisUtil.get(RedisKeys.OUT_OF_STOCK + goodsOrder.getGoodsId());
        if (ObjectUtils.isEmpty(outStock)) {
            redisUtil.set(RedisKeys.OUT_OF_STOCK + goodsOrder.getGoodsId(), 1);

            return Result.error("商品未初始化");
        } else {
            if (Objects.equals("1", String.valueOf(outStock))) {
                int i = orderService.saveGoodsOrder(goodsOrder);
                if (i == 1){
                    System.out.println("保存订单成功,订单id = " + goodsOrder.getId());
                }

                rocketMqProducer.send(MqEnum.Topic.TROY_QIULI_ORDER_SYNC_TOPIC,
                        MqEnum.Tags.TROY_QIULI_ORDER_TO_CONSUMER_TAG,
                        JSON.toJSONString(goodsOrder),
                        "order_key");
            } else {
                return Result.error("商品已卖完");
            }
        }
        return Result.success(GoodsOrderConvert.goodsOrderBo2Vo(goodsOrder));
    }

}
