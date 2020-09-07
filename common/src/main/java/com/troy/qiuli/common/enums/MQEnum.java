package com.troy.qiuli.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author caoqiang
 * @date 2020-09-01 19:42
 * @desc
 */
public interface MQEnum {

    @Getter
    @AllArgsConstructor
    enum Group {
        /// 组
        TROY_QIULI_ORDER_META("TROY_QIULI_ORDER_GROUP", "META-ORDER组"),
        ;

        public String name;
        public String desc;

    }

    @Getter
    @AllArgsConstructor
    enum Topic {

        TROY_QIULI_ORDER_SYNC("TROY_QIULI_ORDER_SYNC_TOPIC", "订单Topic"),
        ;

        public String name;
        public String desc;

    }

    @Getter
    @AllArgsConstructor
    enum Tags {

        TROY_QIULI_ORDER_TO_CONSUMER("TROY_QIULI_ORDER_TO_CONSUMER_TAG", "订单发送给消费者")
        ;

        public String name;
        public String desc;

    }

}
