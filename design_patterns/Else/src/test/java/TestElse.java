import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestElse {
    @Test
    public void test01(){
        double num1 = 2;
        double num2 = 3;

        double l = num1 / num2;

        System.out.println("l = " + l);
    }

    @Test
    public void timestampToLocalDate(){
        long timestamp = 1706457600000L;
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDateTime = " + localDate);
    }

    @Test
    public void getOSInfo(){
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.out.println("Windows");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            System.out.println("Unix/Linux/Mac");
        } else {
            System.out.println("Other");
        }
    }

    @Test
    public void test02(){
        Long num = null;
        String str = num == 0 ? "ONLINE" : "OFFLINE";
        System.out.println("str = " + str);
    }
}
