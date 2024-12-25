import org.junit.Test;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void testListToMap(){
        ArrayList<User> objects = new ArrayList<>();
        User tom = new User(1L, "tom");
        objects.add(tom);
        User jack = new User(2L, "jack");
        objects.add(jack);
        Map<Long, String> collect =
                objects.stream()
                .collect(Collectors.toMap(o -> o.getId(), o -> o.getName()));
        System.out.println("collect = " + collect);
    }

    @Test
    public void testMap(){
        User tom = new User(1L, "tom");
        User jack = new User(2L, "jack");
        System.out.println("jack.hashCode() = " + jack.hashCode());
        System.out.println("tom = " + tom.hashCode());

        HashMap<User, String> userStringHashMap = new HashMap<>();
        userStringHashMap.put(tom, "tom");
        userStringHashMap.put(jack, "jack");
        System.out.println("userStringHashMap = " + userStringHashMap);
    }

    @Test
    public void testFile(){
        File file = new File("D:\\jwm.tar");
        String path = file.getPath();
        System.out.println("path = " + path);

    }

    @Test
    public void testNum(){
        try {
            User tom = new User("tom");
            System.out.println(tom.toString());
        } catch (RuntimeException 李依鹏) {
            System.out.println("出现异常，异常信息: "+ 李依鹏.getMessage());
            throw new RuntimeException(李依鹏);
        }
    }
}

