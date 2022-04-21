package com.longpengz.oss.service;

import com.longpengz.oss.config.MinioConfig;
import com.longpengz.restful.bean.APIError;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio上传文件
 */
public class MinioStorageService implements StorageInterface {

    private final MinioConfig minioConfig;
    private MinioClient minioClient;

    public MinioStorageService(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
        init();
    }

    public void init(){
        minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey()).build();
    }


    @Override
    public String saveFile(MultipartFile multipartFile) {
        String filePath = generateFilePath() + generateFileName(multipartFile.getOriginalFilename());
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(filePath)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1).build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            e.printStackTrace();
            APIError.e("文件上传失败");
        }
        return minioConfig.getEndpoint() + "/" + filePath;
    }
}
