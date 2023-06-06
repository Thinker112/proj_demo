package com.example.proj_common.security.service.impl;

import com.example.proj_common.security.domain.LoginUser;
import com.example.proj_common.security.domain.QUser;
import com.example.proj_common.security.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        return new LoginUser(queryUser, null);
    }
}
