package com.longpengz.oss.service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public interface StorageInterface {

    /**
     * 保存文件
     * @author longpengZ
     * @param multipartFile 上传文件
     */
    String saveFile(MultipartFile multipartFile);

    /**
     * 生成文件名称
     * @author longpengZ
     * @param oldName 文件旧名称
     */
    default String generateFileName(String oldName) {
        String suffix = StringUtils.hasLength(oldName) ? oldName.substring(oldName.lastIndexOf(".") + 1):"";
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + new Random().nextInt(2) + "." + suffix;
    }

    /**
     * 生成文件存储路径
     * @author longpengZ
     */
    default String generateFilePath(){
        Calendar now = Calendar.getInstance();
        return "storage"
                + File.separator + now.get(Calendar.YEAR)
                + File.separator + (now.get(Calendar.MONTH) + 1)
                + File.separator;
    }


}
