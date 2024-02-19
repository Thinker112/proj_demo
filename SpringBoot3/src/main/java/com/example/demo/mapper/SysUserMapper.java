package com.example.demo.mapper;

import com.example.demo.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user")
    List<SysUser> selectAll();
}
