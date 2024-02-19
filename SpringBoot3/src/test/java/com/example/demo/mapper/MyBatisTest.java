package com.example.demo.mapper;

import com.example.demo.bean.SysUser;
import com.example.demo.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyBatisTest{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void test(){
        List<SysUser> list = sysUserMapper.selectAll();
        list.forEach(System.out::println);
    }

}
