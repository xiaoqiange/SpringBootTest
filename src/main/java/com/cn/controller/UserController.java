package com.cn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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

//定义返回值都是json格式
@RestController
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
    
    /**
     * Description:[下载图片]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2019年1月2日
     * @param response
     */
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
    
    @RequestMapping("/test")
    public void test() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        String [] fields = {"name","code","phone","sex","age","birthday"};
        Class<?> c = Class.forName("com.cn.model.User");
        Field[] declaredFields = c.getDeclaredFields();
        List<User> list = userService.getAll();
        for (User user : list) {
            for (int i = 0; i < fields.length; i++) {
                Field field = c.getDeclaredField(fields[i]);
                field.setAccessible(true);
                Object object = field.get(user);
                System.out.println(object);
            }
        }
    }
    
    @RequestMapping("/importExcel")
    public void ImportExcel() throws BiffException, IOException{
        Workbook workbook = Workbook.getWorkbook(new File("D:\\uploadTest\\test.xls"));
        Sheet sheet = workbook.getSheet(0);
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell=sheet.getCell(j, i);
                System.out.println(cell.toString());
            }
        }
    }
    public static void main(String[] args) {
        Mee mee1 = Mee.getInstance();
        System.out.println(mee1);
        Mee mee2 = Mee.getInstance();
        System.out.println(mee2);
        Mee mee3 = Mee.getInstance();
        System.out.println(mee3);
        
        EnumSingleton em1 = EnumSingleton.INSTANCE;
        em1.print();
        EnumSingleton em2 = EnumSingleton.INSTANCE;
        em2.print();
        EnumSingleton em3 = EnumSingleton.INSTANCE;
        em3.print();
    }
}
class Mee {
    private volatile static Mee mee;
    private Mee(){}
    public static Mee getInstance(){
        if(mee ==null){
            synchronized(Mee.class){
                if(mee == null){
                    mee = new Mee();
                }
            }
        }
        return mee;
    }
}

enum EnumSingleton{
    INSTANCE;
    
    private EnumSingleton(){
        System.out.println("init");
    }
    
    public void print(){
        System.out.println("print()");
    }
}