package com.cn.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cn.model.User;
import com.cn.service.UserService;

@RestController
//定义返回值都是json格式
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public Object getAll() {
        List<User> list = userService.getAll();
        return list;
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
    
    @RequestMapping("/download")
    public void downLoadExcle(HttpServletRequest req, HttpServletResponse resp) throws IOException, RowsExceededException, WriteException{
        File file = new File("D:/uploadTest/test.xls");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        //创建工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        //创建工作表
        WritableSheet sheet = workbook.createSheet("用户信息", 0);
        //设置title
        String [] title = {"用户名","用户编码","联系方式","性别","年龄","出生年月"};
        String [] fields = {"name","code","phone","sex","age","birthday"};
        //创建单元格
        Label label = null;
        for (int i = 0; i < title.length; i++) {
            //x,y,第一行的列名
            label = new Label(i, 0, title[i]);
            sheet.addCell(label);
        }
        //填充数据
        List<User> list = userService.getAll();
        for (int i = 0; i < list.size(); i++) {
               label = new Label(0, i+1, list.get(i).getName());
               sheet.addCell(label);
               label = new Label(1, i+1, list.get(i).getCode());
               sheet.addCell(label); 
               label = new Label(2, i+1, list.get(i).getPhone());
               sheet.addCell(label); 
               label = new Label(3, i+1, (list.get(i).getSex()==1?"男":"女"));
               sheet.addCell(label); 
               label = new Label(4, i+1, list.get(i).getAge()+"");
               sheet.addCell(label); 
               label = new Label(5, i+1, list.get(i).getBirthday()+"");
               sheet.addCell(label); 
        }
        workbook.write();
        workbook.close();
        resp.reset();
        resp.setContentType("application/vnd.ms-excel; charset=utf-8");
        resp.setHeader("Content-Disposition", "attachment; filename="+file.getName());
        FileInputStream  in = null;
        in = new FileInputStream(file);
        ServletOutputStream outputStream = resp.getOutputStream();
        IOUtils.copy(in, outputStream);
        outputStream.flush();
        outputStream.close();
        in.close();
    }
}
