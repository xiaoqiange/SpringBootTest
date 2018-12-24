package com.cn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class TestController {
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.cn.controller.MyRunnable");
        Field declaredField = clazz.getDeclaredField("i");
        declaredField.setAccessible(true);
        int i = declaredField.getInt(new MyRunnable());
//        System.out.println("本机的外网ip是 ： "+TestController.getWebIp("http://2018.ip138.com/ic.asp"));
        MyRunnable runnable = new MyRunnable();
        for(int j=0;j < i;j++){
            Thread thread = new Thread(runnable,"柜台1");
            thread.start();
            Thread thread1 = new Thread(runnable,"柜台2");
            thread1.start();
        }
    }
    
    public static String getWebIp(String str){
        System.out.println("开始访问本机ip");
        try{
            URL url = new URL(str);
            System.out.println(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"GBK"));
            String webContent = "" ;
            String s = "" ;
            StringBuffer sb = new StringBuffer("");
            while((s = bufferedReader.readLine())!= null){
                sb.append( s + "\r\n" );
            }
            webContent = sb.toString();
            int start = webContent.indexOf("[",1);
            int end = webContent.indexOf("]");
            webContent = webContent.substring(start+1, end);
            return webContent;
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
}
