package com.yc.electricity.util;


import java.lang.reflect.ParameterizedType;

/**
 * Created by Chuan on 3/20/16.
 */
public class TUtils {


    public static <T> Class<T> getActualType(Class entityClass) {
        ParameterizedType parameterizedType = (ParameterizedType) entityClass.getGenericSuperclass();
        Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        return clazz;
    }
}
