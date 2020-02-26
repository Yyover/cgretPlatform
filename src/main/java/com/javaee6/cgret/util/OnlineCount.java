package com.javaee6.cgret.util;

/**
 * @author Administrator
 **/

public class OnlineCount {

    static Long onlineCount = 0L;

    public static Long getOnlineCount() {
        return onlineCount;
    }

    public static void setOnlineCount(Long onlineCount) {
        OnlineCount.onlineCount = onlineCount;
    }
}
