package com.example.proj_common.security.entity;


import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysRole sysRole = (SysRole) o;
        return getId() != null && Objects.equals(getId(), sysRole.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
