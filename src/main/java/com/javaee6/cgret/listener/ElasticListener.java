package com.javaee6.cgret.listener;

import com.javaee6.cgret.util.PropertiesUtils;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 要在web.xml中配置监听
 * @author Yellowyao
 */
public class ElasticListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        PropertiesUtils.pps = new Properties();

        try {
            String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
            FileInputStream is = new FileInputStream(path + "elasticsearch.properties");
            PropertiesUtils.pps.load(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
