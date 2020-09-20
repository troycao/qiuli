package com.troy.qiuli.common;

import com.troy.qiuli.common.enums.CodeEnum;
import com.troy.qiuli.common.exception.QiuLiException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private String code;
    private String message;
    private Boolean success;
    private T data;

    public Result(String code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Result(CodeEnum codeEnum, Boolean success) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.success = success;
    }

    public static Result<?> success() {
        return new Result<>(CodeEnum.SUCCESS, Boolean.TRUE);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(CodeEnum.SUCCESS, Boolean.TRUE);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(){
        return new Result<>(CodeEnum.SYSTEM_ERROR.getCode(), CodeEnum.SYSTEM_ERROR.getMessage(), Boolean.FALSE);
    }

    public static <T> Result<T> error(QiuLiException exception){
        return new Result<>(exception.getCode(), exception.getMessage(), Boolean.FALSE);
    }

    public static <T> Result<T> error(String message){
        return new Result<T>(CodeEnum.SYSTEM_ERROR.getCode(), message, Boolean.FALSE);
    }

    public static <T> Result<T> error(CodeEnum codeEnum){
        return new Result<>(codeEnum.getCode(), codeEnum.getMessage(), Boolean.FALSE);
    }
}
