package com.cn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class TestController {
    
    public static void main(String[] args) {
        System.out.println("本机的外网ip是 ： "+TestController.getWebIp("http://2018.ip138.com/ic.asp"));
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
