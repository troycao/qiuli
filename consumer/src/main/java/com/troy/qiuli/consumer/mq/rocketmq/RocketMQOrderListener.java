package com.troy.qiuli.consumer.mq.rocketmq;

/**
 * @author caoqiang
 * @date 2020-08-31 14:08
 * @desc
 */

import com.troy.qiuli.common.enums.MQEnum;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息消费监听
 */
//@Component
public class RocketMQOrderListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        System.out.println("test================");
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        System.out.println("接受到的消息为："+new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume ==3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        System.out.println("topic=============" + messageExt.getTopic());
        if(messageExt.getTopic().equals("TROY_QIULI_ORDER_SYNC_TOPIC")){
            String tags = messageExt.getTags() ;
            String name = MQEnum.Tags.TROY_QIULI_ORDER_TO_CONSUMER.getName();
            if (tags.equals(MQEnum.Tags.TROY_QIULI_ORDER_TO_CONSUMER)){
                System.out.println("创建订单 tag == >>" + tags);
            } else {
                System.out.println("未匹配到Tag == >>"+tags);
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
