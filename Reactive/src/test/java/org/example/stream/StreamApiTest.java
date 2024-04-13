package org.example.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiTest {

    /**
     * flatMap() 方法接收一个函数作为参数，该函数将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
    @Test
    public void flatMapTest() {

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .flatMap(i -> Stream.of(i, i + 1, i + 2))
                .forEach(System.out::println);
    }

    @Test
    public void reduceTest(){
        List<String> strings = Arrays.asList("Hello", "World", "Java", "Stream");

        // 使用reduce方法对字符串进行拼接
        String result = strings.stream()
                .reduce("", (a, b) -> a + "," + b)
                .substring(1);
        System.out.println("Result: " + result);
    }

    @Test
    public void reduceTest2() {
        int reduce = IntStream.range(1, 100)
                .reduce(0, Integer::sum);
        System.out.println("reduce = " + reduce);
    }

    @Test
    public void anyMatchTest() {
        List<String> strings = Arrays.asList("Hello", "World", "Java", "Stream");
        boolean anyMatch = strings.stream()
                .anyMatch(s -> s.equals("Stream"));
        System.out.println("anyMatch = " + anyMatch);
    }

    @Test
    public void filterTest(){
        List<String> strings = Arrays.asList("Hello", "World", "Java", "Stream", "");
        long count = strings.stream()
                .filter(s -> !s.equals("Hello"))
                .count();
        System.out.println("count = " + count);
//                .forEach(System.out::println);
    }

    @Test
    public void foreachTest(){
        List<String> strings = Arrays.asList("Hello", "World", "Java", "Stream", "");
        for (String string : strings) {
            if (Objects.equals(string, "World")) continue;
            System.out.println("string = " + string);
        }
    }
}
