package com.troy.qiuli.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.troy.qiuli.dao.AbstractBaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * goods_order
 * @author caoqiang
 * @date 2020-09-02 09:52:04
*/
@Data
@ToString
public class GoodsOrder extends AbstractBaseEntity {
    /**
     * BIGINT(19) 必填<br>
     * 
     */
    private Long id;

    /**
     * VARCHAR(40) 必填<br>
     * 
     */
    private String goodsName;

    /**
     * SMALLINT(5)<br>
     * 
     */
    private Short num;

    /**
     * BIGINT(19)<br>
     * 
     */
    private Long userId;

    /**
     * DECIMAL(10,2)<br>
     * 
     */
    private BigDecimal price;

    /**
     * CHAR(1) 默认值[0]<br>
     * 
     */
    private String status;

    /**
     * TIMESTAMP(26)<br>
     * 
     */
    private Date createTime;

    /**
     * TIMESTAMP(26)<br>
     * 
     */
    private Date updateTime;

    /**
     * VARCHAR(30)<br>
     * 
     */
    private String userName;

    /**
     * BIGINT(19) 必填<br>
     * 
     */
    private Long goodsId;
}