package com.javaee6.cgret.util;

/**
 * @author Administrator
 **/

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    private Date date;

    public DateTimeUtil() {
        this.date = new Date();
    }

    public DateTimeUtil(Date date) {
        this.date = date;
    }

    /**
     * 获取以"-"为分隔符的日期字符串
     * @return
     */
    public String getStringDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取以"- :"为分隔符的日期时间字符串
     * @return
     */
    public String getStringDatetime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取以":"为分隔符的时间字符串
     * @return
     */
    public String getStringTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取毫秒的字符串
     * @return
     */
    public String getStringMilli() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("S");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取无分隔符的日期字符串
     * @return
     */
    public String getStringDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取无分隔符的日期时间字符串
     * @return
     */
    public String getStringDatetimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取无分隔符的时间字符串
     * @return
     */
    public String getStringTimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取无分隔符的日期时间带毫秒字符串
     * @return
     */
    public String getStringDatetimemilliStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取中文日期字符串
     * @return
     */
    public String getStringDateZhStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取中文日期时间字符串
     * @return
     */
    public String getStringDatetimeZhStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取中文时间字符串
     * @return
     */
    public String getStringTimeZhStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH时mm分ss秒");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取中文日期时间带毫秒字符串
     * @return
     */
    public String getStringDatetimemilliZhStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒S毫秒");
        return simpleDateFormat.format(date);
    }

    /**
     * 解析日期字符串(带"-"分隔符)为Date类型
     * @param dateStr
     * @return
     */
    public Date parseDate(String dateStr) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 解析日期时间字符串(带"- :"分隔符)为Date类型
     * @param datetimeStr
     * @return
     */
    public Date parseDatetime(String datetimeStr) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(datetimeStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 解析日期字符串(无分隔符)为Date类型
     * @param dateStr
     * @return
     */
    public Date parseDateStr(String dateStr) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 解析日期时间字符串(无分隔符)为Date类型
     * @param datetimeStr
     * @return
     */
    public Date parseDatetimeStr(String datetimeStr) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            date = simpleDateFormat.parse(datetimeStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

}
