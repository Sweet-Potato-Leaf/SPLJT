package com.splto.oss.controller;

import com.splto.oss.service.FileStorageService;
import com.splto.restful.model.API;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


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
