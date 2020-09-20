package com.troy.qiuli.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统异常定义枚举
 * A开头：标识错误来源于用户
 * B开头：标识错误来源于本系统
 * C开头：标识错误来源于第三方系统
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    SUCCESS("00000", "成功"),

    /**user*/
    USER_IS_NULL("U00001", "用户不存在"),


    SYSTEM_ERROR("S99999", "系统错误"),
    ;

    private final String code;
    private final String message;

}
