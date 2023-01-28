package com.splto.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Optional;

public class FileUtil {

    public static void main(String[] args) {
        String path = "E:\\demo";
        fileDealWith(path, file -> System.out.println(file.getName()),(dir, name) -> name.contains(".txt"));

    }

    /**
     * 读取处理根目录下所有目录文件
     * @author longpengZ
     * @param path 根目录（必须是文件夹）
     * @param fileCallback 文件处理回调
     * @param filenameFilter 文件筛选条件
     */
    public static void fileDealWith(String path,FileCallback fileCallback, FilenameFilter filenameFilter){
        File file = new File(path);
        if(file.isDirectory()){
            Optional.ofNullable(file.listFiles(filenameFilter))
                    .ifPresent(its -> {
                        for (File it : its) {
                            fileCallback.save(it);
                        }
                    });
            Optional.ofNullable(file.listFiles(File::isDirectory))
                    .ifPresent(its -> {
                        for (File it : its) {
                            fileDealWith(it.getPath(),fileCallback,filenameFilter);
                        }
                    });
        }
    }

    public interface FileCallback {
        void save(File file);
    }
}
