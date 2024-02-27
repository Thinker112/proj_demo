package org.example.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiTest {

    @Test
    public void flatMapTest() {

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .flatMap(i -> Stream.of(i, i + 1, i + 2))
                .forEach(System.out::println);
    }

    @Test
    public void reduceTest(){
//        int reduce = IntStream.range(1, 10)
//                .reduce(0, Integer::sum);
//
//        System.out.println("reduce = " + reduce);

        List<String> strings = Arrays.asList("Hello", "World", "Java", "Stream");

        // 使用reduce方法对字符串进行拼接
        String result = strings.stream()
                .reduce("", (a, b) -> a + "," + b)
                .substring(1);
        System.out.println("Result: " + result);

    }
}
