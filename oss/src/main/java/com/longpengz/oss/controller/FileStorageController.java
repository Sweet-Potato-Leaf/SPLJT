package com.longpengz.oss.controller;

import com.longpengz.oss.service.FileStorageService;
import com.longpengz.restful.bean.API;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RequestMapping("/storage")
public class FileStorageController {

    @Resource
    private FileStorageService fileStorageService;

    @ApiOperation("文件上传")
    @PostMapping("/uploadFile")
    public API<String> saveFile(MultipartFile file){
        return API.ok(fileStorageService.saveFile(file));
    }

}
