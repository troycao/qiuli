package com.troy.qiuli.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbstractBaseEntity implements Serializable {

    /**
     * BIGINT(19) 必填<br>
     * 主键，自增id
     */
    //private Long id;
    /**
     * TIMESTAMP(26) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 创建时间
     */
    private Date createTime;

    /**
     * TIMESTAMP(26) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 更新时间
     */
    private Date updateTime;

    public void createInit() {
        if (null == createTime) {
            createTime = new Date();
        }
    }

    public void updateInit() {
        updateTime = new Date();
    }

}
