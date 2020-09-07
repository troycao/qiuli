package com.troy.qiuli.consumer.mq.rocketmq.config;

import com.troy.qiuli.common.enums.MQEnum;
import com.troy.qiuli.consumer.mq.rocketmq.RocketMsgListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author caoqiang
 * @date 2020-08-31 14:05
 * @desc
 */
@Configuration
public class ConsumerConfig {

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.groupName}")
    private String groupName;

    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;

    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;

    @Value("${rocketmq.consumer.topics}")
    private String topics;

    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;

    /*@Resource
    private RocketMsgListener msgListener;*/

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
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

            if(messageExt.getTopic().equals(MQEnum.Topic.TROY_QIULI_ORDER_SYNC.getName())){
                String tags = messageExt.getTags() ;
                switch (tags){
                    case "TROY_QIULI_ORDER_TO_CONSUMER_TAG":
                        System.out.println("创建订单 tag == >>"+tags);
                        break ;
                    default:
                        System.out.println("未匹配到Tag == >>"+tags);
                        break;
                }
            }
            // 消息消费成功
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            String[] topicTagsArr = topics.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }
}
