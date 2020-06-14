package com.boot.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  WangShuo 
 * @date 创建时间：2018年9月6日 下午1:34:39 
 * @version 1.0 
 * @Description: TODO
 * @since 1.8
 * @return  
 */
public class ObjectUtils {

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    
}
