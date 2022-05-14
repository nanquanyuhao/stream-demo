package net.nanquanyuhao.stream;

import java.util.stream.IntStream;

/**
 * 1. 中间操作放回的都是 Stream
 * 2. 中间操作函数共同任务就是对 Stream 流中的每一个数据都进行操作
 * 3. 一个中间函数对于数据池的操作结果将作为下一个中间操作函数的数据输入
 * 4. 所有中间操作不可逆，一旦操作就会影响数据池中的数据
 */
public class Tests1 {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
    }

    public static void test01() {

        int[] nums = {1, 2, 3};
        int sum = IntStream.of(nums)
                .map(i -> i * 2) // 中间操作 {2, 4, 6}
                .map(i -> i * i) // 中间操作 {4, 16, 36}
                .sum();          // 终止操作
        System.out.println(sum);
    }

    public static void test02() {

        int[] nums = {1, 2, 3};
        int sum = IntStream.of(nums)
                .map(i -> i * 2) // 中间操作 {2, 4, 6}
                .map(i -> {
                    System.out.println(i + "进行乘方");
                    return i * i;
                })               // 中间操作 {4, 16, 36}
                .sum();          // 终止操作
        System.out.println(sum);
    }

    private static int compute(int n) {
        System.out.println(n + "进行乘方");
        return n * n;
    }

    public static void test03() {

        int[] nums = {1, 2, 3};
        int sum = IntStream.of(nums)
                .map(i -> i * 2)      // 中间操作 {2, 4, 6}
                .map(Tests1::compute) // 中间操作 {4, 16, 36}
                .sum();               // 终止操作
        System.out.println(sum);
    }

    public static void test04() {

        int[] nums = {1, 2, 3};
        IntStream.of(nums)
                .map(i -> i * 2)      // 中间操作 {2, 4, 6}
                .map(Tests1::compute);
    }
}
