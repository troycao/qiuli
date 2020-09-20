package com.troy.qiuli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author caoqiang
 * @date 2020-09-20 17:43
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrderDto {

    private Long id;

    private String goodsName;

    private Short num;

    private Long userId;

    private BigDecimal price;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String userName;

    private Long goodsId;
}
