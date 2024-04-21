package org.example.reactor;

import java.util.Date;

/**
 * @Author yyb <br>
 * @Create 2024-04-16
 */
public class BooleanTest {
    public static void main(String[] args) {
        Date date = new Date();
        boolean before = date.before(new Date(1713272280067L));
        System.out.println("before = " + before);

    }
}
