package com.javaee6.cgret.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现登录唯一性的工具类
 * 全局变量sessionIdMap
 * @author Administrator
 **/

public class SessionSave  {

    private static Map<String, String> sessionIdMap = new HashMap<>();

    public static Map<String, String> getSessionIdMap() {
        return sessionIdMap;
    }

    public static void setSessionIdMap(Map<String, String> sessionIdMap) {
        SessionSave.sessionIdMap = sessionIdMap;
    }
}
