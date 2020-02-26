package com.javaee6.cgret.util;

import com.javaee6.cgret.model.Client;

/**
 * 用户线程共享类
 * @author Administrator
 **/

public class ClientThreadLocal {

    private static ThreadLocal<Client> local =  new ThreadLocal<Client>();

    public static void set(Client client){
        local.set(client);
    }

    public static Client get(){
        return local.get();
    }

    public  static void remove(){
        local.remove();
    }
}
