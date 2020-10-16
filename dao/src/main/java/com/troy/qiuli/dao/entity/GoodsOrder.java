package com.troy.qiuli.dao.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * goods_order
 * @author caoqiang
 * @date 2020-10-16 11:59:02
*/
@Data
@ToString
public class GoodsOrder {
    /**
     * BIGINT(19) 必填<br>
     * 
     */
    private Long id;

    /**
     * VARCHAR(40)<br>
     * 
     */
    private String goodsName;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer num;

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
     * CHAR(1)<br>
     * 
     */
    private String status;

    /**
     * TIMESTAMP(26) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 
     */
    private Date createTime;

    /**
     * TIMESTAMP(26) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 
     */
    private Date updateTime;

    /**
     * VARCHAR(40)<br>
     * 
     */
    private String userName;

    /**
     * BIGINT(19)<br>
     * 
     */
    private Long goodsId;
}