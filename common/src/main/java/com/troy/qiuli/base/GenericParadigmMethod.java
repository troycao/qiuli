package com.troy.qiuli.base;

import com.troy.qiuli.bo.UserBO;

/**
 * @author caoqiang
 * @date 2020-10-10 10:01
 * @desc java泛型方法
 */
public class GenericParadigmMethod {

    public static void main(String[] args) {
        GenericParadigmMethod method = new GenericParadigmMethod();
        try {
            UserBO userBO = (UserBO)method.getObject(Class.forName("com.troy.qiuli.bo.UserBO"));
            System.out.println(userBO.getClass());
            System.out.println(userBO.getId());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public <T> T getObject(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t = tClass.newInstance();
        return t;
    }
}
