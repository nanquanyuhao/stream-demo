package net.nanquanyuhao;

import java.util.stream.Stream;

/**
 * forEach 方法测试
 */
public class ForEachTest {

    public static void main(String[] args) {
        test01();
        test02();
    }

    /**
     * 对于流的并行操作，forEach() 处理结果是无序的
     */
    public static void test01() {

        String words = "Beijing is the capital of China";
        Stream.of(words.split(" "))
                .parallel()
                .forEach(System.out::println);
    }

    /**
     * 对于流的并行操作，forEachOrdered() 处理结果是有序的
     */
    public static void test02() {

        String words = "Beijing is the capital of China";
        Stream.of(words.split(" "))
                .parallel()
                .forEachOrdered(System.out::println);
    }
}
