package net.nanquanyuhao;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. 使用 Arrays 中的 stream() 方法，将数组转成流
 * 2. 使用 Stream 中的 of() 方法，将数组转成流
 */
public class ArraysTest {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    public static void test01() {

        Integer[] nums = new Integer[10];
        Stream<Integer> stream = Arrays.stream(nums);

        System.out.println(stream);
    }

    public static void test02() {

        int[] nums = {1, 2, 3};
        IntStream stream = IntStream.of(nums);
        IntStream stream1 = Arrays.stream(nums);

        System.out.println(IntStream.of(nums).sum());
        System.out.println(Arrays.stream(nums).sum());
    }

    public static void test03() {
        // 创建一个包含 1,2,3 的 Stream
        IntStream stream = IntStream.of(1, 2, 3);

        // 创建一个包含 [1, 5) 的 Stream
        IntStream range1 = IntStream.range(1, 5);
        // 创建一个包含 [1, 5] 的 Stream
        IntStream range2 = IntStream.rangeClosed(1, 5);

        System.out.println(range1.sum());
        System.out.println(range2.sum());

        // new Random().ints() 创建一个无限流
        // limit(5) 限制流中元素个数为 5 个
        IntStream ints = new Random().ints().limit(5);
        System.out.println(" ===== " + new Random().ints(5).sum());

        System.out.println(new Random().ints(5).peek(System.out::println).sum());
    }
}
