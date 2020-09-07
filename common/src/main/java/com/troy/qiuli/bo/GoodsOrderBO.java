package com.troy.qiuli.bo;

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
public class GoodsOrderBO implements Serializable {

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
