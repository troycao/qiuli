package com.troy.qiuli.consumer.mq.rocketmq.convert;

import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.dto.GoodsOrderDto;
import org.springframework.beans.BeanUtils;

/**
 * @author caoqiang
 * @date 2020-09-20 17:53
 * @desc
 */
public class GoodsOrderConvert {

    /**
     * DTO转bo
     * @param goodsOrderDto
     * @return
     */
    public static GoodsOrder goodsOrderDto2Bo(GoodsOrderDto goodsOrderDto) {
        GoodsOrder goodsOrder = new GoodsOrder();
        BeanUtils.copyProperties(goodsOrderDto, goodsOrder);
        return goodsOrder;
    }


}
