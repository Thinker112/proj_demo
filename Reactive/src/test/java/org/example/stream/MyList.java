package org.example.stream;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
class MyList<T> {
    private List<T> list = new ArrayList<>();

    public boolean add(T t) {
        return list.add(t);
    }

    /**
     * 给MyList传递具体的判断规则，然后MyList把内部现有符合判断（true）的元素集返回
     * @param predicate
     * @return
     */
    public MyList<T> filter(Predicate<T> predicate){
        MyList<T> filteredList = new MyList<>();

        for (T t : list) {
            if (predicate.test(t)) {
                // 收集判断为true的元素
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * 把MyList中的List<T>转为List<R>
     *
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> MyList<R> map(Function<T, R> mapper) {
        MyList<R> mappedList = new MyList<>();

        for (T t : list) {
            mappedList.add(mapper.apply(t));
        }

        return mappedList;
    }

}