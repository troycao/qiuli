package com.troy.qiuli.dao.entity;

import lombok.Data;
import lombok.ToString;

/**
 * user
 * @author caoqiang
 * @date 2020-10-16 11:59:02
*/
@Data
@ToString
public class User {
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