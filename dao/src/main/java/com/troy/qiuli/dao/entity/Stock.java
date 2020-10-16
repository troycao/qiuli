package com.troy.qiuli.dao.entity;

import lombok.Data;
import lombok.ToString;

/**
 * stock
 * @author caoqiang
 * @date 2020-10-16 11:59:02
*/
@Data
@ToString
public class Stock {
    /**
     * BIGINT(19) 必填<br>
     * 
     */
    private Long id;

    /**
     * BIGINT(19)<br>
     * 
     */
    private Long goodsId;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer num;
}