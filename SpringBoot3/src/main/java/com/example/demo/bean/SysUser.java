package com.example.demo.bean;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;


@Getter
@Setter
@ToString
@Entity
@Table(name = "sys_user")
@QueryEntity
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @Column(name = "dept_id")
    private Long deptId;

    @Size(max = 30)
    @NotNull
    @Column(name = "nick_name", nullable = false, length = 30)
    private String nickName;

    @Size(max = 2)
    @Column(name = "user_type", length = 2)
    private String userType;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 11)
    @Column(name = "phonenumber", length = 11)
    private String phonenumber;

    @Column(name = "sex")
    private Character sex;

    @Column(name = "avatar", length = 100)
    private String avatar;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "status")
    private Character status;

    @Column(name = "del_flag")
    private Character delFlag;

    @Column(name = "login_ip", length = 128)
    private String loginIp;

    @Column(name = "login_date")
    private Instant loginDate;

    @Column(name = "create_by", length = 64)
    private String createBy;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_by", length = 64)
    private String updateBy;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "remark", length = 500)
    private String remark;

}