package com.troy.qiuli.consumer.mq.rocketmq.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * @author caoqiang
 * @date 2020-09-20 16:55
 * @desc
 */
public abstract class AbstractConsumer {

    /**
     * MQ主机地址
     */
    @Value("${rocketmq.consumer.namesrvAddr}")
    String namesrvAddr;

    DefaultMQPushConsumer consumer;

    abstract public String getGroup();

    abstract public String getTopic();

    abstract public String getTags();

    abstract public void registerMessageListener();

    @PostConstruct
    public void initConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(getGroup());
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setVipChannelEnabled(false);
        /**
         * MessageModel：消息模式
         * MessageModel.CLUSTERING 集群模式：每个节点平均消费
         * MessageModel.BROADCASTING 广播模式：每个消息都会被消费一遍
         */
        consumer.setMessageModel(MessageModel.CLUSTERING);
        /**
         * ConsumeFromWhere：启动时消费位置
         * CONSUME_FROM_LAST_OFFSET：一个新的订阅组第一次启动从队列的最后位置开始消费,后续再启动接着上次消费的进度开始消费
         * CONSUME_FROM_FIRST_OFFSET：一个新的订阅组第一次启动从队列的最前位置开始消费,后续再启动接着上次消费的进度开始消费
         * CONSUME_FROM_TIMESTAMP： 一个新的订阅组第一次启动从指定时间点开始消费，后续再启动接着上次消费的进度开始消费，时间点设置参见DefaultMQPushConsumer.consumeTimestamp参数
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(getTopic(), getTags());
        this.registerMessageListener();
        consumer.start();
        System.out.println("consumer starting");
    }
}
