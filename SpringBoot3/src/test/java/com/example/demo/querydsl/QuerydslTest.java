package com.example.demo.querydsl;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author yueyubo
 * @date 2024-07-07
 */
public class QuerydslTest {


    @Test
    public void test() {

        // 创建一个SecureRandom实例
        SecureRandom random = new SecureRandom();

        // 生成64字节的随机密钥
        byte[] keyBytes = new byte[64];
        random.nextBytes(keyBytes);

        // 编码为Base64字符串
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);

        // 打印Base64编码的密钥
        System.out.println("Generated secret key (Base64 encoded): " + encodedKey);

        // 打印编码前的原始密钥（字节数组形式）
        System.out.println("Original secret key (byte array): " + Arrays.toString(keyBytes));
    }
}
