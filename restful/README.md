# Restful接口工具库

## 概述
对Restful接口使用的一些便捷封装，统一的异常处理，统一的API返回参数，相关接口文档依赖及配置。

## 用法

### 1.maven依赖
```xml
<dependency>
    <groupId>com.longpengz</groupId>
    <artifactId>restful</artifactId>
    <version>${spljt.version}</version>
</dependency>
```

### 2.springboot 项目 properties配置
```properties
# 开启文档访问 访问地址/doc.html
swagger.enable= true
```

### 3.springboot 项目使用示例

```java
import com.longpengz.restful.bean.API;
import com.longpengz.restful.bean.APIError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "示例接口")
@RestController
@RequiredArgsConstructor
public class DemoController {

    private final Demo demo;

    @GetMapping("test")
    @ApiOperation("测试")
    public API<String> test(@RequestParam String type) {
        demo.test(type);
        return API.ok("成功");
    }
}

@Service
public class Demo {

    public void test(String type) {
        if(type.equals("1")){
            APIError.e("抛出异常");
        }
    }
}
```


