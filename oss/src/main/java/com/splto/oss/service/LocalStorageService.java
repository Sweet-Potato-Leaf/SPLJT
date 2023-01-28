package com.splto.oss.service;

import com.splto.oss.config.LocalConfig;
import com.splto.restful.model.APIError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 本地上传文件
 * @author longpengZ
 */
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
            e.printStackTrace();
            APIError.e("文件上传失败");
        }
        filePath = filePath.replace("\\", "/");
        return localConfig.getAddress() + "/" + filePath;
    }

}
