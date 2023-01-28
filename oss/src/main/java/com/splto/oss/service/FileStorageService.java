package com.splto.oss.service;

import com.splto.oss.config.FileStorageConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final FileStorageConfig fileStorageConfig;
    private StorageInterface storageInterface;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;
        init();
    }

    public void init(){
        switch (fileStorageConfig.getType()) {
            case "local" -> storageInterface = new LocalStorageService(fileStorageConfig.getLocalConfig());
            case "minio" -> storageInterface = new MinioStorageService(fileStorageConfig.getMinioConfig());
            default -> throw new RuntimeException("未配置文件存储");
        }
    }

    /**
     * 保存文件
     * @author longpengZ
     */
    public String saveFile(MultipartFile multipartFile){
        return storageInterface.saveFile(multipartFile);
    }


}
