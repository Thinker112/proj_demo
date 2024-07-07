package com.example.demo.repository;

import com.example.demo.bean.QSysUser;
import com.example.demo.bean.SysUser;
import io.github.openfeign.querydsl.jpa.spring.repository.QuerydslJpaRepository;

/**
 * @author yueyubo
 * @date 2024-07-07
 */
public interface SysUserRepository extends QuerydslJpaRepository<SysUser, Long> {
    QSysUser sysUser = QSysUser.sysUser;
    default SysUser findByUsername(String username) {
        return selectFrom(sysUser).where(sysUser.userName.eq(username))
                .fetchOne();
    }
    default SysUser findByUsernameAndSex(String username, Character sex) {
        return selectFrom(sysUser).where(sysUser.userName.eq(username)
                                    .and(sysUser.sex.eq(sex)))
                .fetchOne();
    }
}
