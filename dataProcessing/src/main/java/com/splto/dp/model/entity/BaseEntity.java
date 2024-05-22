package com.splto.dp.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     * 序列化id
     */
    @Serial
    private static final long serialVersionUID = 4125096758372084309L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @ApiModelProperty("唯一标识")
//    @Column(updatable = false,columnDefinition = "int(11) comment 'id自增'")
//    private Integer id;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Schema(title = "唯一标识")
    @Column(updatable = false,columnDefinition = "varchar(64) comment 'uuid'")
    private String id;


    @CreationTimestamp
    @Schema(title = "创建时间")
    @Column(updatable = false,columnDefinition = "datetime(0) comment '创建时间'")
    private Timestamp createTime;


    @UpdateTimestamp
    @Schema(title = "更新时间")
    @Column(columnDefinition = "datetime(0) comment '更新时间'")
    private Timestamp updateTime;

    @Schema(title = "0-删除 1-新建")
    @Column(insertable = false,columnDefinition = "int default 1")
    private Integer presenceStatus;


}
