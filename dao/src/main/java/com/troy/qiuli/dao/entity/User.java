package com.troy.qiuli.dao.entity;

import com.troy.qiuli.dao.AbstractBaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * user
 * @author caoqiang
 * @date 2020-09-02 09:52:05
*/
@Data
@ToString
public class User extends AbstractBaseEntity {
    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer id;

    /**
     * VARCHAR(20)<br>
     * 
     */
    private String username;

    /**
     * VARCHAR(30)<br>
     * 
     */
    private String password;
}