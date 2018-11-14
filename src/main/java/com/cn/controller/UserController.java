package com.cn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cn.service.UserService;

@RestController
//定义返回值都是json格式
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public Object getAll() {
        return userService.getAll();
    }

    @RequestMapping("/upload")
    public Object upload(HttpServletRequest arg0, HttpServletResponse response) {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) arg0;
        List<MultipartFile> files = request.getFiles("file");
        String path = "D:/uploadTest/";
        for (MultipartFile multipartFile : files) {
            String fileName = multipartFile.getOriginalFilename();
            if(fileName.indexOf("\\")!= -1){
                fileName= fileName.substring(fileName.lastIndexOf("\\"));
            }
            String filePath = path + fileName;
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(multipartFile.getBytes());
                outputStream.flush();
                outputStream.close();
                return filePath;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    @RequestMapping("/downLoad")
    public void downLoad(HttpServletResponse response){
        String filePath="D:/uploadTest/tupian.png";
        FileInputStream fileInputStream = null;
        File file = new File(filePath);
        try {
            fileInputStream= new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(fileInputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
