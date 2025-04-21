package com.splto.oss.service;

import com.splto.oss.config.LocalConfig;
import com.splto.restful.model.APIError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 本地上传文件
 * @author longpengZ
 */
@Slf4j
public class LocalStorageService implements StorageInterface {

    private final LocalConfig localConfig;

    public LocalStorageService(LocalConfig localConfig) {
        this.localConfig = localConfig;
    }

    @Override
    public String saveFile(MultipartFile multipartFile) {
        String filePath = generateFilePath()
                + generateFileName(multipartFile.getOriginalFilename());
        String path = localConfig.getUploadPath()
                + File.separator + filePath;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("文件上传失败:", e);
            APIError.e("文件上传失败");
        }
        filePath = filePath.replace("\\", "/");
        return localConfig.getAddress() + "/" + filePath;
    }

    @Override
    public String saveFile(byte[] bytes, String fileName) {
        fileName = generateFileName(fileName);
        String partPath = generateFilePath();
        String filePath = partPath + fileName;
        String path = localConfig.getUploadPath()
                + File.separator + filePath;
        String parentPath = localConfig.getUploadPath()
                + File.separator + partPath;
        File parentFile = new File(parentPath);
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        File file = new File(path);
        try (FileOutputStream fos = new FileOutputStream(file)){
//            file.createNewFile();
            fos.write(bytes);
        } catch (IOException e) {
            log.error("文件上传失败:", e);
            APIError.e("文件上传失败");
        }
        filePath = filePath.replace("\\", "/");
        return localConfig.getAddress() + "/" + filePath;
    }

}
