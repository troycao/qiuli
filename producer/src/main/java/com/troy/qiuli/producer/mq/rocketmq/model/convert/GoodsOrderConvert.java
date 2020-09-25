package com.troy.qiuli.producer.mq.rocketmq.model.convert;

import cn.hutool.core.util.IdUtil;
import com.troy.qiuli.dao.entity.GoodsOrder;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;
import org.springframework.beans.BeanUtils;

/**
 * @author caoqiang
 * @date 2020-09-20 17:53
 * @desc
 */
public class GoodsOrderConvert {

    /**
     * vo转bo
     * @param goodsOrderVo
     * @return
     */
    public static GoodsOrder goodsOrderVo2Bo(GoodsOrderRequestVo goodsOrderVo) {
        GoodsOrder goodsOrder = new GoodsOrder();
        BeanUtils.copyProperties(goodsOrderVo, goodsOrder);
        goodsOrder.setId(IdUtil.createSnowflake(1,1).nextId());
        return goodsOrder;
    }

    /**
     * vo转bo
     * @param goodsOrder
     * @return
     */
    public static GoodsOrderRequestVo goodsOrderBo2Vo(GoodsOrder goodsOrder) {
        GoodsOrderRequestVo goodsOrderVo = new GoodsOrderRequestVo();
        BeanUtils.copyProperties(goodsOrder, goodsOrderVo);
        return goodsOrderVo;
    }

}
