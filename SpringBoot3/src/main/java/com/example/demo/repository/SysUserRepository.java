package com.example.demo.repository;

import com.example.demo.bean.QSysUser;
import com.example.demo.bean.SysUser;
import io.github.openfeign.querydsl.jpa.spring.repository.QuerydslJpaRepository;

/**
 * @author yueyubo
 * @date 2024-07-07
 */
public interface SysUserRepository extends QuerydslJpaRepository<SysUser, Long> {

    default SysUser findByUsername(String username) {
        return selectFrom(QSysUser.sysUser).where(QSysUser.sysUser.userName.eq(username)).fetchOne();
    }
}
