package org.example.stream;

import org.junit.Test;

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
        int reduce = IntStream.range(1, 10)
                .reduce(0, Integer::sum);

        System.out.println("reduce = " + reduce);
    }
}
