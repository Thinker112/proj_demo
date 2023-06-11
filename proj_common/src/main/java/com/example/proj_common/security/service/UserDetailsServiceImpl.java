package com.example.proj_common.security.service;

import com.example.proj_common.security.domain.LoginUser;
import com.example.proj_common.security.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QUser user = QUser.user;
        User queryUser = queryFactory.selectFrom(user)
                .where(user.userName.eq("yyb"))
                .fetchOne();

        if (Objects.isNull(queryUser)){
            throw new RuntimeException("用户名或密码错误");
        }

        // 根据用户查询权限信息 添加到LoginUser中
        QUserRole userRole = QUserRole.userRole;
        QSysRole sysRole = QSysRole.sysRole;
        QRoleMenu roleMenu = QRoleMenu.roleMenu;
        QMenu menu = QMenu.menu;
        List<String> permList = queryFactory.select(menu.perms)
                .from(user)
                .leftJoin(userRole).on(user.id.eq(userRole.userId))
                .leftJoin(sysRole).on(userRole.roleId.eq(sysRole.id))
                .leftJoin(roleMenu).on(sysRole.id.eq(roleMenu.roleId))
                .leftJoin(menu).on(menu.id.eq(roleMenu.menuId))
                .where(user.id.eq(queryUser.getId())
                        .and(sysRole.status.eq(String.valueOf(0)))
                        .and(menu.status.eq(String.valueOf(0))))
                .distinct()
                .fetch();


        return new LoginUser(queryUser, permList);
    }
}
