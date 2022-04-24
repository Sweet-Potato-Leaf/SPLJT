# 存储仓库

## 概述
快速集成对象存储相关内容

## 用法

### 1.maven依赖
```xml
<dependency>
    <groupId>com.longpengz</groupId>
    <artifactId>oss</artifactId>
    <version>${spljt.version}</version>
</dependency>
```

### 2.springboot 项目 properties配置
```properties
# 本地存储
com.longpengz.file-storage.type = local
com.longpengz.file-storage.local-config.upload-path = /home/app/upload
com.longpengz.file-storage.local-config.address = http://localhost:8080
```

```properties
# minio oss 存储
com.longpengz.file-storage.type = minio
com.longpengz.file-storage.minio-config.endpoint = http://storage.minio.com
com.longpengz.file-storage.minio-config.accessKey = accessKey
com.longpengz.file-storage.minio-config.secretKey = secretKey
com.longpengz.file-storage.minio-config.bucket-name = bucketName
```

### 3.springboot 项目使用示例

```java
import com.longpengz.oss.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class Demo {
    
    private final FileStorageService fileStorageService;

    public void saveFile(MultipartFile file) {
        fileStorageService.saveFile(file);
    }
}
```


