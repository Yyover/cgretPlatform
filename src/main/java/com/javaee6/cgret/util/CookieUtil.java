package com.javaee6.cgret.util;

/**
 * @author Administrator
 **/

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil

{

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtil.class);



    public static void writeCookie(HttpServletResponse response, String key, String value)

    {

        writeCookie(response, key, value, 2592000, "/");

    }



    public static void writeCookie(HttpServletResponse response, String key, String value, int maxAge, String path)

    {

        try

        {

            Cookie cookie = new Cookie(key, URLEncoder.encode(value, "utf-8") );


            cookie.setMaxAge(maxAge);



            cookie.setPath(path);

            response.addCookie(cookie);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    public static String getCookieValue(HttpServletRequest request, String key) throws UnsupportedEncodingException {

        Cookie[] cc = request.getCookies();

        if (cc == null)

            return null;

        for (Cookie cookie : cc) {

            if (cookie.getName().equals(key)) {

                return URLDecoder.decode(cookie.getValue(), "utf-8");

            }

        }

        return null;

    }



    public static void removeCookie(String name, HttpServletRequest request, HttpServletResponse response)

    {

        Cookie[] cc = request.getCookies();

        if ((cc == null) || (cc.length < 1))

        {

            LOGGER.error("请求里cookie为null");

        }

        for (Cookie cookie : cc)

            if (cookie.getName().equals(name)) {

                cookie.setMaxAge(0);

                cookie.setPath("/");

                response.addCookie(cookie);

            }

    }

}