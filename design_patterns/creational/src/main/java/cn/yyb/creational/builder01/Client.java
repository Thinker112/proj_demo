package cn.yyb.creational.builder01;

import java.security.SecureRandom;
import java.util.Base64;

public class Client {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[64]; // 64 bytes should be enough for a secure key.
        random.nextBytes(keyBytes);
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated secret key: " + encodedKey);
    }
}
