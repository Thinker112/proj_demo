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
@Table(name = "sys_role_menu")
@Entity
@QueryEntity
public class RoleMenu {

    @Id
    private Long roleId;

    private Long menuId;

}
