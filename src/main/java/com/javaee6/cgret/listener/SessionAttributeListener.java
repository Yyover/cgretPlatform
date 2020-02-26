package com.javaee6.cgret.listener;

import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.model.OrderCart;
import com.javaee6.cgret.util.ClientThreadLocal;
import com.sun.org.apache.xpath.internal.operations.Equals;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Administrator
 **/

public class SessionAttributeListener implements HttpSessionAttributeListener {

    /**
     * 创建session时候触发
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if("clientInfo".equals(event.getName())){
            ClientThreadLocal.set((Client) event.getValue());
        }
        if("shopCart".equals(event.getName())){
            System.out.println("创建的session是shopcart");
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if("clientInfo".equals(event.getName())){
            ClientThreadLocal.remove();
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if("clientInfo".equals(event.getName())){
            ClientThreadLocal.set((Client) event.getValue());
        }
    }
}
