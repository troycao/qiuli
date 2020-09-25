package com.troy.qiuli.producer.mq.rocketmq.controller;

import com.troy.qiuli.common.Result;
import com.troy.qiuli.common.enums.CodeEnum;
import com.troy.qiuli.common.utils.Asserts;
import com.troy.qiuli.producer.mq.rocketmq.model.vo.GoodsOrderRequestVo;
import com.troy.qiuli.producer.mq.rocketmq.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caoqiang
 * @date 2020-08-31 15:00
 * @desc
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class SendController {

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("/sendOrder")
    public Result<?> sendOrder (@RequestBody(required = false) GoodsOrderRequestVo goodsOrderVo){
        /**GoodsOrderBO goodsOrderBO = new GoodsOrderBO(
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
        logger.info("创建订单,输入参数:{}", goodsOrderVo);
        Asserts.isFalse(ObjectUtils.isEmpty(goodsOrderVo), CodeEnum.SYSTEM_PARAS_IS_NULL);
        Result<?> result = orderService.createGoodsOrder(goodsOrderVo);
        logger.info("创建订单结束,返回数据:{}", result);
        return result;
    }
}
