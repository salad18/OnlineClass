package com.xuecheng.media;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * @Author: SS
 * @DATE: 2024/1/12 16:02
 * @Decription: 测试视频上传
 * @Version 1.0
 **/
public class BigFileTest {

    // 分块
    @Test
    public void testChunk() throws IOException {
        // 源文件
        File file = new File("C:\\Users\\94417\\Desktop\\9e93a5866e0fcb0bff0aea13d883abb8.mp4");
        // 分块文件储存路径
        String chunkFilePath = "C:\\Users\\94417\\Desktop\\chunk\\";
        // 分块文件的大小
        int chunkSize = 1024 * 1024 * 5;
        // 分块文件的个数
        int chunkNum = (int) Math.ceil(file.length() * 1.0 / chunkSize);
        // 使用流从源文件读数据，向分块文件中写数据
        RandomAccessFile raf_r = new RandomAccessFile(file, "r");

        // 缓冲区
        byte[] bytes = new byte[1024];
        for (int i = 0; i < chunkNum; i++) {
            File chunkFile = new File(chunkFilePath + i);
            // 分块文件的写入流
            RandomAccessFile raf_rw = new RandomAccessFile(chunkFile, "rw");
            int len = -1;
            while ((len = raf_r.read(bytes)) != -1) {
                raf_rw.write(bytes, 0, len);
                if (chunkFile.length() >= chunkSize) {
                    break;
                }
            }
            raf_rw.close();
        }
        raf_r.close();
    }

    // 将分块进行合并
    @Test
    public void testMerge() throws IOException {
        // 分块文件储存路径
        File chunkFolder = new File("C:\\Users\\94417\\Desktop\\chunk\\");
        // 合并文件
        File mergeFile = new File("C:\\Users\\94417\\Desktop\\合并.mp4");
        // 源文件
        File sourceFile = new File("C:\\Users\\94417\\Desktop\\9e93a5866e0fcb0bff0aea13d883abb8.mp4");

        // 取出所有的分块文件
        File[] files = chunkFolder.listFiles();

        Arrays.sort(files, (File o1, File o2)->{
            return Integer.parseInt(o1.getName()) - Integer.parseInt(o2.getName());
        });

        // 向合并文件写的流
        RandomAccessFile raf_rw = new RandomAccessFile(mergeFile, "rw");

        // 缓冲区
        byte[] bytes = new byte[1024];

        // 遍历分块文件
        for (File file : files) {
            // 读分块的流
            RandomAccessFile raf_r = new RandomAccessFile(file, "r");

            int len = -1;
            while ((len=raf_r.read(bytes))!=-1){
                raf_rw.write(bytes,0,len);
            }
            raf_r.close();
        }
        raf_rw.close();

        // 合并文件验证
        FileInputStream fileInputStream_merge = new FileInputStream(mergeFile);
        String s = DigestUtils.md5DigestAsHex(fileInputStream_merge);

        FileInputStream fileInputStream_source = new FileInputStream(sourceFile);
        String s1 = DigestUtils.md5DigestAsHex(fileInputStream_source);
        if (s.equals(s1)) {
            System.out.println("合并成功");
        }
    }


}
