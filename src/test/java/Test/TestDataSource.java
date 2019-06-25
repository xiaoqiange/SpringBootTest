package Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.cn.SpringBootApplicationTest;
import com.cn.mapper.UserMapper;
import com.cn.model.Scores;

@SpringBootTest(classes = SpringBootApplicationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestDataSource {
    @Resource
    private DataSource dataSource;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        System.out.println("11111"+this.dataSource);
    }
    
    
    @Test
    public void testExcel() throws IOException, RowsExceededException, WriteException{
        File file = new File("D:/test.xls");
//        if(!file.exists()){
//            file.mkdir();
//        }
        file.createNewFile();
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
            for (int j = 0; j < fields.length; j++) {
                System.out.println(fields[i].startsWith("get"));
            }
            //x,y,第一行的列名
            label = new Label(i, 0, title[i]);
            sheet.addCell(label);
        }
        
        //填充数据
//        List<User> list = userService.getAll();
//        for (int i = 0; i < list.size(); i++) {
//               label = new Label(i, i+1, list.get(i).getName());
//               sheet.addCell(label);
//               label = new Label(i, i+1, list.get(i).getCode());
//               sheet.addCell(label); 
//               label = new Label(i, i+1, list.get(i).getPhone());
//               sheet.addCell(label); 
//               label = new Label(i, i+1, list.get(i).getSex().toString());
//               sheet.addCell(label); 
//               label = new Label(i, i+1, list.get(i).getAge().toString());
//               sheet.addCell(label); 
//               label = new Label(i, i+1, list.get(i).getBirthday().toGMTString());
//               sheet.addCell(label); 
//        }
    }
    @Test
    public void mybatisCache(){
        System.out.println("测试mybatis懒加载！");
        List<Scores> scores = userMapper.getScores();
        System.out.println(JSON.toJSONString(scores.get(0).getStudent(), true));
    }
}
