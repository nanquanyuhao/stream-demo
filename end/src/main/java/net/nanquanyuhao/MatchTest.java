package net.nanquanyuhao;

import java.util.Arrays;
import java.util.List;

/**
 * 匹配、聚合操作
 */
public class MatchTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // 接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回 true，否则返回 false，结果：false
        boolean allMatch = list.stream()
                .allMatch(e -> e > 10);
        System.out.println(allMatch);

        // 接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回 true，否则返回 false，结果：true
        boolean noneMatch = list.stream()
                .noneMatch(e -> e > 10);
        System.out.println(noneMatch);
        
        // 接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回 true，否则返回 false，结果：true
        boolean anyMatch = list.stream()
                .anyMatch(e -> e > 4);
        System.out.println(anyMatch);

        // 返回流中第一个元素，结果：1
        Integer findFirst = list.stream()
                .findFirst()
                .get();
        System.out.println(findFirst);
        // 返回流中的任意元素，找到任何一个即结束查找，故大概率为第一个
        Integer findAny = list.stream()
                .findAny()
                .get();
        System.out.println(findAny);

        // 返回流中元素的总个数，结果：5
        long count = list.stream().count();
        System.out.println(count);

        // 返回流中元素最大值，结果：5
        Integer max = list.stream()
                .max(Integer::compareTo).get();
        System.out.println(max);
        // 返回流中元素最小值，结果：1
        Integer min = list.stream()
                .min(Integer::compareTo).get();
        System.out.println(min);
    }
}
