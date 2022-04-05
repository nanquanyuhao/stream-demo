package net.nanquanyuhao;

import java.util.stream.Stream;

/**
 * 筛选与切片
 */
public class FilterTest {

    public static void main(String[] args) {

        // Stream 静态方法 of() 直接创建 Stream
        Stream<Integer> stream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);

        // 1. 过滤出大于 5 的值，结果为：6 6 7 9 8 10 12 14 14
        Stream<Integer> newStream = stream.filter(s -> s > 5)
                // 2. 排重，通过流中元素的 hashCode() 和 equals() 去除重复元素，结果为：6 7 9 8 10 12 14
                .distinct()
                // 3. 开头开始跳过 2 元素，配合 limit(n) 可实现分页，结果为：9 8 10 12 14
                .skip(2)
                // 4. 获取最前面两个，结果为：9 8
                .limit(2);

        newStream.forEach(System.out::println);
    }
}
