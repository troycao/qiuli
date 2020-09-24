package com.troy.qiuli.producer.mq.rocketmq.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.troy.qiuli.bo.GoodsOrderBO;
import com.troy.qiuli.common.Result;
import com.troy.qiuli.common.enums.CodeEnum;
import com.troy.qiuli.common.utils.Asserts;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;
import com.troy.qiuli.producer.mq.rocketmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author caoqiang
 * @date 2020-08-31 15:00
 * @desc
 */
@RestController
@RequestMapping("/order")
public class SendController {

    @Autowired
    OrderService orderService;

    @PostMapping("/sendOrder")
    public Result<?> sendOrder (@RequestBody(required = false) GoodsOrderRequestVo goodsOrderVo){

        /*GoodsOrderBO goodsOrderBO = new GoodsOrderBO(
                IdUtil.createSnowflake(1,1).nextId(),
                "大衣",
                (short) 5,
                10000000000000000L,
                new BigDecimal(128.8),
                "1",
                DateUtil.date(),
                DateUtil.date(),
                "张三"
                ,1L);

        System.out.println(JSON.toJSONString(goodsOrderBO));*/
        //log.info()
        Asserts.isFalse(ObjectUtils.isEmpty(goodsOrderVo), CodeEnum.SYSTEM_PARAS_IS_NULL);
        Result<?> result = orderService.createGoodsOrder(goodsOrderVo);
        // log.info("");
        return result;
    }
}
