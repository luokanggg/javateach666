package com.ctbu.javateach666;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ctbu.javateach666.pojo.po.User;
 
public class ExportExeclTest {
     
    public static void main(String[] args) {
        new ExportExeclTest().exportData2CSV();
    }
 
    private String fileName;
 
    public void exportData2CSV() {
        List<User> novels = getNovels();
        fileName = "D:/luokan.csv";
        writeData2CSV(novels, fileName);
        System.out.println("D:/novels.csv下文件生成成功");
    }
 
    private void writeData2CSV(List<User> novels, String fileName2) {
        //FileWriter fw = null;
    	OutputStreamWriter fw = null;
        try {
            //fw = new FileWriter(fileName);
        	fw = new OutputStreamWriter(new FileOutputStream(fileName),"GBK");
            // 输出标题头
            // 注意列之间用","间隔,写完一行需要回车换行"\r\n"
            String title = "序号,小说名称,作者,出版日期\r\n";
            fw.write(title);
 
            String content = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < novels.size(); i++) {
                User novel = novels.get(i);
                // 注意列之间用","间隔,写完一行需要回车换行"\r\n"
                content = (i + 1) + "," + novel.getUsername() + ","
                        + novel.getPassword() + ","
                        + novel.getAge() + "\r\n";
                fw.write(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    private List<User> getNovels() {
        List<User> novels = new ArrayList<User>();
 
        User novel1 = new User();
        User novel2 = new User();
        User novel3 = new User();
        User novel4 = new User();
        novel1.setUsername("风云第一刀");
        novel1.setPassword("古龙");
        novel1.setAge(1);
        
        novel2.setUsername("陆小凤传奇");
        novel2.setPassword("古龙");
        novel2.setAge(2);
        
        novel3.setUsername("书剑恩仇录");
        novel3.setPassword("金庸");
        novel3.setAge(3);
        
        novel4.setUsername("鹿鼎记");
        novel4.setPassword("金庸");
        novel4.setAge(4);
 
        novels.add(novel1);
        novels.add(novel2);
        novels.add(novel3);
        novels.add(novel4);
 
        return novels;
    }
 
}
