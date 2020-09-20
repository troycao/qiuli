package com.troy.qiuli.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author caoqiang
 * @date 2020-09-01 19:42
 * @desc
 */
public interface MqEnum {

    @Getter
    @AllArgsConstructor
    enum Group {
        /// 组
        TROY_QIULI_ORDER_GROUP("TROY_QIULI_ORDER_GROUP", "ORDER组"),
        ;

        public String name;
        public String desc;

    }

    @Getter
    @AllArgsConstructor
    enum Topic {

        TROY_QIULI_ORDER_SYNC_TOPIC("TROY_QIULI_ORDER_SYNC_TOPIC", "订单Topic"),
        ;

        public String name;
        public String desc;

    }

    @Getter
    @AllArgsConstructor
    enum Tags {

        TROY_QIULI_ORDER_TO_CONSUMER_TAG("TROY_QIULI_ORDER_TO_CONSUMER_TAG", "订单发送给消费者")
        ;

        public String name;
        public String desc;

    }

}
