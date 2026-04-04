package com.penguin.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云OSS工具类
 */
@Component
public class OssUtils {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.urlPrefix}")
    private String urlPrefix;

    /**
     * 上传文件到OSS
     * @param file 上传的文件
     * @return 上传后的公网访问URL
     */
    public String uploadFile(MultipartFile file) throws Exception {
        // 1. 生成唯一文件名，避免重名覆盖
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = "penguin/" + UUID.randomUUID().toString().replace("-", "") + suffix;

        // 2. 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try (InputStream inputStream = file.getInputStream()) {
            // 3. 上传文件到OSS
            ossClient.putObject(bucketName, fileName, inputStream);
            // 4. 拼接文件的公网URL
            return urlPrefix + "/" + fileName;
        } finally {
            // 5. 关闭OSS客户端
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}