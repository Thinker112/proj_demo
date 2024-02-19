package com.example.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class SysUser {

    private Long id;

    private Long deptId;

    private String userName;

    private String nickName;

    private String userType;

    private String email;

    private String phonenumber;

    private Character sex;

    private String avatar;

    private String password;

    private Character status;

    private Character delFlag;

    private String loginIp;

    private LocalDateTime loginDate;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private String remark;

}