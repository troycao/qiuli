package com.troy.qiuli.producer.mq.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.troy.qiuli.common.Result;
import com.troy.qiuli.common.constants.Constants;
import com.troy.qiuli.common.enums.MqEnum;
import com.troy.qiuli.common.redis.RedisUtil;
import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.producer.mq.rocketmq.model.convert.GoodsOrderConvert;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;
import com.troy.qiuli.producer.mq.rocketmq.product.RocketMqProducer;
import com.troy.qiuli.producer.mq.rocketmq.service.OrderService;
import com.troy.qiuli.service.OrderBizService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Result<?> createGoodsOrder(GoodsOrderRequestVo goodsOrderVo) {
        GoodsOrder goodsOrder = GoodsOrderConvert.goodsOrderVo2Bo(goodsOrderVo);
        // redisson分布式锁
        RLock lock = redissonClient.getLock(Constants.REDISSON_GOODS_ORDER_PREFIX + goodsOrder.getGoodsId());
        lock.lock();

        try {
            Object outStock = redisUtil.get(Constants.OUT_OF_STOCK + goodsOrder.getGoodsId());
            if (ObjectUtils.isEmpty(outStock)) {
                redisUtil.set(Constants.OUT_OF_STOCK + goodsOrder.getGoodsId(), 1);

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
        } finally {
            lock.unlock();
        }
        return Result.success(GoodsOrderConvert.goodsOrderBo2Vo(goodsOrder));
    }

}
