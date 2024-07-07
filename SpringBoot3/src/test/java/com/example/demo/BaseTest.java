package com.example.demo;

import com.example.demo.bean.SysUser;
import com.example.demo.repository.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    @Autowired
    SysUserRepository sysUserRepository;

    @Test
    public void test(){
        SysUser admin = sysUserRepository.findByUsername("admin");
        System.out.println("admin = " + admin);
    }

}