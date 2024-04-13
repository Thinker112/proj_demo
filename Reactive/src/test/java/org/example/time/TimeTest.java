package org.example.time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeTest {

    @Test
    public void getDayOfWeek(){
//        LocalDate localDate = LocalDate.now(); // 获取当前日期
//        DayOfWeek dayOfWeek = localDate.getDayOfWeek(); // 获取星期信息
        DayOfWeek dayOfWeek = LocalDate.now().minusDays(1).getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek.ordinal());
        DayOfWeek dayOfWeek1 = DayOfWeek.of(4);
        boolean b = dayOfWeek == dayOfWeek1;
        System.out.println("dayOfWeek1 = " + b);
        // 打印星期的全名
        System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));
    }
}
