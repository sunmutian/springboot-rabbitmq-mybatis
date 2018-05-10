package com.tian.utils;

import java.util.UUID;

/**
 * rabbitmq常量类
 *
 * @author tianweichang
 * @date 2018-05-08 11:53
 **/
public class StringUtils {

    /**
     * 获取uuid
     */
    public static String UUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isEmpty(String str){
        return org.springframework.util.StringUtils.isEmpty(str);
    }
}
