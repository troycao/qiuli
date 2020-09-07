package com.troy.qiuli.dao.entity;

import java.math.BigDecimal;

import com.troy.qiuli.dao.AbstractBaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * goods
 * @author caoqiang
 * @date 2020-09-02 09:52:05
*/
@Data
@ToString
public class Goods extends AbstractBaseEntity {
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