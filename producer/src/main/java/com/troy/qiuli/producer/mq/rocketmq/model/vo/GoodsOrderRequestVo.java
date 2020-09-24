package com.troy.qiuli.producer.mq.rocketmq.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author caoqiang
 * @date 2020-09-01 11:43
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("商品订单信息VO")
public class GoodsOrderRequestVo implements Serializable {

    @ApiModelProperty("订单ID")
    private Long id;

    @ApiModelProperty("订单名称")
    private String goodsName;

    private Short num;

    private Long userId;

    private BigDecimal price;

    private String status;

    private String userName;

    private Long goodsId;
}
