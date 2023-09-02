package com.example.proj_common.security.entity;


import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "sys_role_menu")
@Entity
@QueryEntity
public class RoleMenu {

    @Id
    private Long roleId;

    private Long menuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleMenu roleMenu = (RoleMenu) o;
        return getRoleId() != null && Objects.equals(getRoleId(), roleMenu.getRoleId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
