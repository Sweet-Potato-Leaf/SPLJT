# 缓存

## 概述
在项目中快速集成缓存相关功能。目前支持redis缓存快速接入

## 用法

### 1.maven依赖
```xml
<dependency>
    <groupId>com.longpengz</groupId>
    <artifactId>cache</artifactId>
    <version>${spljt.version}</version>
</dependency>
```

### 2.springboot 项目 properties配置
```properties
# redis
# spring redis 默认配置都继承
com.longpengz.cache.type=redis
```

### 3.springboot 项目中使用示例

```java

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Demo1 {
    @Resource
    private CacheService cacheService;
}

@Service
public class Demo2 {
    @Autowired
    private CacheService cacheService;
}

@Service
@RequiredArgsConstructor
public class Demo3 {
    private final CacheService cacheService;
    
    public void test(){
        // 通过cacheService 调用封装方法 
        cacheService.saveObject("key", "object", 1, 1);
        
        // 更多封装方法详情看com.longpengz.cache.service.CacheInterface 信息
    }
}


```

