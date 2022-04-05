package net.nanquanyuhao;

import java.util.Arrays;
import java.util.List;

/**
 * 规约操作
 */
public class ComputeTest {

    public static void main(String[] args) {

        // 经过测试，当元素个数小于 24 时，并行时线程数等于元素个数，当大于等于 24 时，并行时线程数为 16
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        // 第一次执行时，accumulator 函数的第一个参数为流中的第一个元素，第二个参数为流中元素的第二个元素；
        // 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推
        // 即此方法的最终结果是进行全部元素的加和计算
        Integer v = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);

        // 流程跟上面一样，只是第一次执行时，accumulator 函数的第一个参数为 identity
        // 第二个参数为流中的第一个元素，最终结果：310
        Integer v1 = list.stream().reduce(10, (x1, x2) -> x1 + x2);
        System.out.println(v1);

        // 在串行流(stream)中，该方法跟第二个方法一样，即第三个参数 combiner 不会起作用
        // 即最终结果为求从 0 开始减到最后一个元素的差值，结果：-300
        Integer v2 = list.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v2);

        // 在并行流(parallelStream)中，我们知道流被 fork join 出多个线程进行执行，此时每个线程的执行流程就跟第二个方法 reduce(identity,accumulator) 一样
        // 第三个参数 combiner 函数，则是将每个线程的执行结果当成一个新的流，然后使用第一个方法 reduce(accumulator) 流程进行规约，结果：-775946240
        Integer v3 = list.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v3);
    }
}
