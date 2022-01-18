package com.longpengz.oss.service;

import com.longpengz.oss.config.MinioConfig;
import com.longpengz.restful.bean.APIError;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
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
        try {
            minioClient = new MinioClient(minioConfig.getEndpoint() , minioConfig.getAccessKey(), minioConfig.getSecretKey());
        } catch (InvalidEndpointException | InvalidPortException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String saveFile(MultipartFile multipartFile) {
        String filePath = generateFilePath() + generateFileName(multipartFile.getOriginalFilename());
        try {
            minioClient.putObject(minioConfig.getBucketName(),
                    filePath,
                    multipartFile.getInputStream(),
                    new PutObjectOptions(multipartFile.getInputStream().available(), -1));
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException e) {
            e.printStackTrace();
            APIError.e("文件上传失败");
        }
        return minioConfig.getEndpoint() + "/" + filePath;
    }
}
