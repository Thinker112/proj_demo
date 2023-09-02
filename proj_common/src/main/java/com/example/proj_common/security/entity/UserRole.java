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
@Table(name = "sys_user_role")
@Entity
@QueryEntity
public class UserRole {

    @Id
    private Long userId;

    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRole userRole = (UserRole) o;
        return getUserId() != null && Objects.equals(getUserId(), userRole.getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
