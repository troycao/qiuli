package com.troy.qiuli.consumer.mq.rocketmq.mq;

import com.alibaba.fastjson.JSONObject;
import com.troy.qiuli.common.constants.RedisKeys;
import com.troy.qiuli.common.enums.MqEnum;
import com.troy.qiuli.common.redis.RedisUtil;
import com.troy.qiuli.consumer.mq.rocketmq.convert.GoodsOrderConvert;
import com.troy.qiuli.consumer.mq.rocketmq.service.StockService;
import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.dao.entity.Stock;
import com.troy.qiuli.dto.GoodsOrderDto;
import org.apache.logging.log4j.util.Strings;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author caoqiang
 * @date 2020-09-20 17:16
 * @desc
 */
@Component
public class OrderSyncConsumer extends AbstractConsumer{

    @Override
    public String getGroup() {
        return MqEnum.Group.TROY_QIULI_ORDER_GROUP.name;
    }

    @Override
    public String getTopic() {
        return MqEnum.Topic.TROY_QIULI_ORDER_SYNC_TOPIC.name;
    }

    @Override
    public String getTags() {
        return MqEnum.Tags.TROY_QIULI_ORDER_TO_CONSUMER_TAG.name;
    }

    @Autowired
    StockService stockService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void registerMessageListener() {
        consumer.registerMessageListener((MessageListenerConcurrently) (mags, context)->{
            for (MessageExt messageExt : mags) {
                try {
                    String json = new String(messageExt.getBody());
                    if (Strings.isEmpty(json)) {
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }

                    GoodsOrderDto goodsOrderDto = JSONObject.parseObject(json, GoodsOrderDto.class);
                    GoodsOrder goodsOrder = GoodsOrderConvert.goodsOrderDto2Bo(goodsOrderDto);

                    Stock stock = new Stock();
                    stock.setGoodsId(goodsOrder.getGoodsId());
                    int i = stockService.updateStock(stock);

                    if (Objects.equals(i, 1)){
                        System.out.println("保存成功");
                    } else {
                        redisUtil.set(RedisKeys.OUT_OF_STOCK + stock.getGoodsId(), 0);
                    }
                } catch (Exception e) {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
    }
}
