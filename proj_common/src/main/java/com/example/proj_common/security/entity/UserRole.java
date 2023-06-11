package com.example.proj_common.security.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user_role")
@Entity
@QueryEntity
public class UserRole {

    @Id
    private Long userId;

    private Long roleId;
}
