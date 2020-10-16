package com.troy.qiuli.dao.entity;

import lombok.Data;
import lombok.ToString;

/**
 * role
 * @author caoqiang
 * @date 2020-10-16 11:59:02
*/
@Data
@ToString
public class Role {
    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer id;

    /**
     * VARCHAR(20)<br>
     * 
     */
    private String roleName;
}