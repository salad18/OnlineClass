package com.xuecheng.media;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import io.minio.errors.*;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: SS
 * @DATE: 2024/1/6 20:59
 * @Decription: TODO
 * @Version 1.0
 **/
public class MinioTest {

    // 通过拓展名获得媒体资源类型 mimetype
    ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(".mp4");
    String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;//通用mimeType，字节流

    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://192.168.101.65:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();

    // 上传文件
    @Test
    public void test_upload() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // 上传文件的参数信息
        UploadObjectArgs testbucket = UploadObjectArgs.builder()
                .bucket("mediafiles") //确定桶
                .filename("C:\\Users\\94417\\Desktop\\大熊猫\\大熊猫.jpeg") // 本地文件路径
                .object("大熊猫.jpeg") //对象名
                .build();

        minioClient.uploadObject(testbucket);

    }

    // 删除文件
    @Test
    public void test_delete() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // 删除文件的参数信息

        RemoveObjectArgs testbucket = RemoveObjectArgs.builder().bucket("mediafiles").object("大熊猫.jpeg").build();

        minioClient.removeObject(testbucket);

    }

    @Test
    public void test_get() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        GetObjectArgs mediafiles = GetObjectArgs.builder().bucket("mediafiles").object("大熊猫.jpeg").build();

        // 查询远程服务获取的流
        FilterInputStream object = minioClient.getObject(mediafiles);
        // 指定输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\94417\\Desktop\\大熊猫.jpeg");
        IOUtils.copy(object, fileOutputStream);

        // 校验完整性 md5
        String s = DigestUtils.md5DigestAsHex(object);
        String s1 = DigestUtils.md5DigestAsHex(new FileInputStream("C:\\Users\\94417\\Desktop\\大熊猫.jpeg"));
        if (s.equals(s1)) {
            System.out.println("下载成功");
        }

    }

    // 把文件上传到minio
    @Test
    public void test_uploadChunk() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        for (int i = 0; i < 3; i++) {
            // 上传文件的参数信息
            UploadObjectArgs testbucket = UploadObjectArgs.builder()
                    .bucket("video") //确定桶
                    .filename("C:\\Users\\94417\\Desktop\\chunk\\" + i) // 本地文件路径
                    .object("chunk/" + i) //对象名
                    .build();

            minioClient.uploadObject(testbucket);
            System.out.println("上传分块" + i + "成功");
        }

    }

    // 调用minio接口合并分块
    @Test
    public void testMerge() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        List<ComposeSource> sources = new ArrayList<>();
//        // 指定分块文件的信息
//        for (int i = 0; i < 3; i++) {
//            ComposeSource composeSource = ComposeSource.builder().bucket("video").object("chunk/" + i).build();
//            sources.add(composeSource);
//        }

        List<ComposeSource> sources = Stream.iterate(0, i -> ++i).limit(3).map(i -> ComposeSource.builder().bucket("video").object("chunk/" + i).build()).collect(Collectors.toList());


        // 指定合并后的文件名
        ComposeObjectArgs video = ComposeObjectArgs.builder()
                .bucket("video")
                .object("merge.mp4")
                .sources(sources)// 指定源文件
                .build();

        // 合并文件，size 262144 must be greater than 5242880，minio默认分块文件为5M
        minioClient.composeObject(video);
    }

    // 批量清理分块文件
}
