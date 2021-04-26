package com.test.mytestdemo.read;

import com.test.mytestdemo.TestApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

public class Test {
    File cacheDir = TestApplication.getContext().getExternalCacheDir();
    String cache=cacheDir.getAbsolutePath() + File.separator + "recorder";

    public void fileInputStreamTest(){
        File cacheDir2 = new File(cache);
        if (!cacheDir2.exists()) {
            cacheDir2.mkdirs();
        }

        try {
            FileInputStream fis=new FileInputStream(cacheDir2+File.separator + "test.txt");
            byte[] buf = new byte[1024];
            int hasRead = 0;
            //read()返回的是单个字节数据（字节数据可以直接专程int类型)，但是read(buf)返回的是读取到的字节数，真正的数据保存在buf中
            while ((hasRead = fis.read(buf)) > 0){
                //每次最多将1024个字节转换成字符串，这里tmp2.txt中的字符小于1024，所以一次就读完了
                //循环次数 = 文件字符数 除以 buf长度
                System.out.println(new String(buf, 0 ,hasRead));

            }
            fis.close();

        }catch (Exception e){

        }


    }
    public void fileReaderTest(){
        try {
            File cacheDir2 = new File(cache);
            if (!cacheDir2.exists()) {
                cacheDir2.mkdirs();
            }
            // 在try() 中打开的文件， JVM会自动关闭
            FileReader fr = new FileReader(cacheDir2+File.separator + "test.txt");
            char[] buf = new char[32];
            int hasRead = 0;
            // 每个char都占两个字节，每个字符或者汉字都是占2个字节，因此无论buf长度为多少，总是能读取中文字符长度的整数倍,不会乱码
            while ((hasRead = fr.read(buf)) > 0){
                // 如果buf的长度大于文件每行的长度，就可以完整输出每行，否则会断行。
                // 循环次数 = 文件字符数 除以 buf长度
                System.out.println(new String(buf, 0, hasRead));

            }

        }catch (Exception e){

        }
    }

    public void fileOutputStream(){
        File cacheDir2 = new File(cache);
        if (!cacheDir2.exists()) {
            cacheDir2.mkdirs();
        }
        try {
            FileInputStream fis = new FileInputStream(cacheDir2+File.separator + "test.txt");
            FileOutputStream fos = new FileOutputStream(cacheDir2+File.separator + "test2.txt");
            byte[] buf = new byte[4];
            int hasRead = 0;
            while ((hasRead = fis.read(buf)) > 0){
                fos.write(buf, 0, hasRead);
            }
            System.out.println("write success");


        }catch (Exception e){

        }

    }
    public void fileWriterTest(){
        File cacheDir2 = new File(cache);
        if (!cacheDir2.exists()) {
            cacheDir2.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(cacheDir2+File.separator + "test.txt");
            fw.write("天王盖地虎\r\n");
            fw.write("宝塔镇河妖\r\n");

        }catch (Exception e){

        }
    }
    public void stringTest(){
        String str = "天王盖地虎\n" + "宝塔镇河妖\n";
        char[] buf = new char[32];
        int hasRead = 0;
        //StringReader将以String字符串为节点读取数据
        try {
            StringReader sr = new StringReader(str);
            while ((hasRead = sr.read(buf)) > 0){
                System.out.print(new String(buf, 0, hasRead));
            }

            //由于String是一个不可变类，因此创建StringWriter时，实际上是以一个StringBuffer作为输出节点
            StringWriter sw = new StringWriter();
            sw.write("黑夜给了我黑色的眼睛\n");
            sw.write("我却用它寻找光明\n");
            //toString()返回sw节点内的数据
            System.out.println(sw.toString());

        }catch (Exception e){

        }


    }


    public void keyIn(){
        try {
            //InputStreamReader是从byte转成char的桥梁
            InputStreamReader reader = new InputStreamReader(System.in);
            //BufferedReader(Reader in)是char类型输入的包装类
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null){
                if (line.equals("exit")){
                    break;
                }
                System.out.println(line);
            }


        }catch (Exception e){

        }
    }

    public void pushBack(){

    }

}
