package com.troy.qiuli.producer.mq.rocketmq.product;

import com.troy.qiuli.common.enums.MQEnum;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author caoqiang
 * @date 2020-09-01 19:41
 * @desc
 */
@Component
public class RocketMQProducer {

    @Autowired
    private DefaultMQProducer producer;

    @PostConstruct
    private void init() {
        producer.setVipChannelEnabled(false);
    }

    /*public void send(MQEnum.Topic topic, MQEnum.Tags tags, String msg, String keys) {
        try {
            System.out.println("[MQ]发送MQ消息内容:{}" + msg);
            Message message = new Message(topic.getName(), tags.getName(), msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setKeys(keys);
            SendResult result = producer.send(message);
            System.out.println("[MQ]发送响应,MsgId:{},发送状态:{}" + result.getMsgId());
        } catch (Exception e) {
            System.out.println("[MQ]发送消息异常" + e);
        }
    }*/

    public SendResult send(MQEnum.Topic topic, MQEnum.Tags tags, String msg, String keys) {
        SendResult result = null;
        try {
            System.out.println("[MQ]发送MQ消息内容:{}" + msg);
            Message message = new Message(topic.getName(), tags.getName(), msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setKeys(keys);
            result = producer.send(message);
            System.out.println("[MQ]发送响应,MsgId:{},发送状态:{}" + result.getMsgId());

        } catch (Exception e) {
            System.out.println("[MQ]发送消息异常" + e);
        }
        return result;
    }

}
