package com.troy.qiuli.dao.entity;

import com.troy.qiuli.dao.AbstractBaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * stock
 * @author caoqiang
 * @date 2020-09-02 09:52:05
*/
@Data
@ToString
public class Stock extends AbstractBaseEntity {
    /**
     * BIGINT(19)<br>
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