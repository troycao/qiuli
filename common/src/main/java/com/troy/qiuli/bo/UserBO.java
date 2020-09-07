package com.troy.qiuli.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author caoqiang
 * @date 2020-09-03 14:22
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserBO {

    private Long id;
    private String password;
    private String username;

}
