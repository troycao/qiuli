package com.troy.qiuli.common.exception;

import com.troy.qiuli.common.enums.CodeEnum;
import lombok.Getter;

@Getter
public class QiuLiException extends RuntimeException {

    private final String code;

    public QiuLiException(String message){
        super(message);
        this.code = CodeEnum.SYSTEM_ERROR.getCode();
    }

    public QiuLiException(CodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }



}
