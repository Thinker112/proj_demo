package org.example.stream;

import org.junit.Test;

import java.util.stream.Stream;

public class FlatMapTest {

    @Test
    public void flatMapTest() {

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .flatMap(i -> Stream.of(i, i + 1, i + 2))
                .forEach(System.out::println);

    }
}
