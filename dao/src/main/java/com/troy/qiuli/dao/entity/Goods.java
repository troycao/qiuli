package com.troy.qiuli.dao.entity;

import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

/**
 * goods
 * @author caoqiang
 * @date 2020-10-16 11:59:02
*/
@Data
@ToString
public class Goods {
    /**
     * BIGINT(19) 必填<br>
     * 
     */
    private Long id;

    /**
     * VARCHAR(200)<br>
     * 
     */
    private String name;

    /**
     * DECIMAL(10,2)<br>
     * 
     */
    private BigDecimal price;
}