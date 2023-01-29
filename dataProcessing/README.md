# 基础数据工具库

## 概述
数据库相关操作基础工具，jpa工具和mybatis工具。

## 用法

### 1.maven依赖
```xml
<dependency>
    <groupId>com.longpengz</groupId>
    <artifactId>dataProcessing</artifactId>
    <version>${spljt.version}</version>
</dependency>
```

### 2.项目中使用示例

```java

import entity.bean.com.splto.dp.BaseEntity;
import pojo.bean.com.splto.dp.PageResult;
import pojo.bean.com.splto.dp.SeachForm;
import pojo.bean.com.splto.dp.SpecificationUtil;
import repository.com.splto.dp.BaseRepository;
import org.apache.ibatis.annotations.Mapper;
import lombok.*;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.data.domain.Page;

import javax.persistence.Entity;

// 数据实体
@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private String name;

}

// jpa查询实体
public interface UserRepository extends BaseRepository<User> {
}

// mybatis查询实体
@Mapper
public interface UserMapper {
    @SelectProvider(type = UserProvider.class, method = "getUsers")
    List<User> getUsers(UserFilter userFilter);

    class UserProvider {
        public String getUsers(UserFilter userFilter) {
            return new SQL() {{
                SELECT("*");
                FROM("user");
                if (!ObjectUtils.isEmpty(userFilter.getName())) {
                    WHERE("name like concat('%',#{name},'%')");
                }
            }}.toString();
        }
    }
}

// mybatis筛选参数实体
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter {
    private String name;
}

@Service
@RequiredArgsConstructor
public class Demo {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // Mybatis 分页查询
    public PageResult<User> getUsers1(SeachForm seachForm) {
        PageHelper.startPage(seachForm.getPageNum(), seachForm.getPageSize());
        List<User> users = userMapper.getUsers(SpecificationUtil.fieldFilterOfObj(UserFilter.class, seachForm.getFieldStrs()));
        return PageResult.of(users);
    }

    // jpa 分页查询
    public PageResult<User> getUsers2(SeachForm seachForm) {
        Page<User> users = userRepository.findAll(SpecificationUtil.<User>filter(seachForm)
                .eq("presenceStatus", 1).build(), seachForm.pageRequest());
        return PageResult.jpaOf(users);
    }


}

```

