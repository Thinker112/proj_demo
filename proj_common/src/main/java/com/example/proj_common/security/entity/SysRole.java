package com.example.proj_common.security.entity;


import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_role")
@Entity
@QueryEntity
public class SysRole {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String roleKey;

    private String status;

    private Integer delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String remark;

    private Long createBy;

    private Long updateBy;
}
