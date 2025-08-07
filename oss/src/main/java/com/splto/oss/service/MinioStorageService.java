package com.splto.oss.service;

import com.splto.oss.config.MinioConfig;
import com.splto.restful.model.APIError;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio上传文件
 */
@Slf4j
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
            log.error("文件上传失败:", e);
            APIError.e("文件上传失败");
        }
        return minioConfig.getEndpoint() + "/" + filePath;
    }

    @Override
    public String saveFile(byte[] bytes, String fileName) {
        String filePath = generateFilePath() + generateFileName(fileName);
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(filePath)
                    .stream(new ByteArrayInputStream(bytes), bytes.length, -1).build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            log.error("文件上传失败:", e);
            APIError.e("文件上传失败");
        }
        return minioConfig.getEndpoint() + "/" + filePath;
    }
}
