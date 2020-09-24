package com.troy.qiuli.common.utils;

import com.troy.qiuli.common.enums.CodeEnum;
import com.troy.qiuli.common.exception.QiuLiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * asserts
 *
 * @author wangjun
 * @date 2020/6/30
 **/
public class Asserts {

    private Asserts() {
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new QiuLiException(message);
        }
    }

    public static void isTrue(boolean expression, CodeEnum codeEnum) {
        if (!expression) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void isTrue(boolean expression, CodeEnum codeEnum, String extra) {
        if (!expression) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new QiuLiException(message);
        }
    }

    public static void isFalse(boolean expression, CodeEnum codeEnum) {
        if (expression) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void isFalse(boolean expression, CodeEnum codeEnum, String extra) {
        if (expression) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

    public static void isBlank(String context, CodeEnum codeEnum) {
        if (StringUtils.isNotBlank(context)) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void notBlank(String context, CodeEnum codeEnum) {
        if (StringUtils.isBlank(context)) {
            throw new QiuLiException(codeEnum);
        }
    }


    public static void notBlank(String context, CodeEnum codeEnum, String extra) {
        if (StringUtils.isBlank(context)) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void isNull(Object object, CodeEnum codeEnum) {
        if (null != object) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void isNull(Object object, CodeEnum codeEnum, String extra) {
        if (null != object) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

    public static void notNull(Object object, CodeEnum codeEnum) {
        if (null == object) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void notNull(Object object, String message) {
        if (null == object) {
            throw new QiuLiException(message);
        }
    }

    public static void notNull(Object object, CodeEnum codeEnum, String extra) {
        if (null == object) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

    public static void notEmpty(Collection<?> list, String message) {
        if (CollectionUtils.isEmpty(list)) {
            throw new QiuLiException(message);
        }
    }

    public static void notEmpty(Collection<?> list, CodeEnum codeEnum, String extra) {
        if (CollectionUtils.isEmpty(list)) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

    public static void equals(Object o1, Object o2, CodeEnum codeEnum) {
        if (!Objects.equals(o1, o2)) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void equals(Object o1, Object o2, CodeEnum codeEnum, String extra) {
        if (!Objects.equals(o1, o2)) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

    public static void notEquals(Object o1, Object o2, CodeEnum codeEnum) {
        if (Objects.equals(o1, o2)) {
            throw new QiuLiException(codeEnum);
        }
    }

    public static void notEquals(Object o1, Object o2, CodeEnum codeEnum, String extra) {
        if (Objects.equals(o1, o2)) {
            throw new QiuLiException(codeEnum, extra);
        }
    }

}
