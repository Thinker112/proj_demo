package com.example.demo.bean;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.*;


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
//    @Column(name = "user_id")
    private Long userId;

//    @Column(name = "user_name")
    private String userName;
}