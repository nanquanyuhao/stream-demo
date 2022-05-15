package net.nanquanyuhao;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 规约操作
 */
public class ComputeTest {

    public static void main(String[] args) {

        // 获取前 24 个自然数，即 1~24
        List<BigInteger> list = Stream.generate(new NaturalSupplier()).limit(24).collect(Collectors.toList());

        // 第一次执行时，accumulator 函数的第一个参数为流中的第一个元素，第二个参数为流中元素的第二个元素；
        // 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推
        // 即此方法的最终结果是进行全部元素的加和计算
        BigInteger v = list.stream()
                .reduce((x1, x2) -> x1.add(x2))
                .get();
        System.out.println(v);

        // 流程跟上面一样，只是第一次执行时，accumulator 函数的第一个参数为 identity
        // 第二个参数为流中的第一个元素，最终结果：310
        BigInteger v1 = list.stream()
                .reduce(BigInteger.valueOf(10), (x1, x2) -> x1.add(x2));
        System.out.println(v1);

        // 在串行流(stream)中，该方法跟第二个方法一样，即第三个参数 combiner 不会起作用
        // 即最终结果为求从 0 开始减到最后一个元素的差值，结果：-300
        BigInteger v2 = list.stream().reduce(BigInteger.valueOf(0),
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1.subtract(x2);
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1.multiply(x2);
                });
        System.out.println(v2);

        // 在并行流(parallelStream)中，我们知道流被 fork join 出多个线程进行执行，此时每个线程的执行流程就跟第二个方法 reduce(identity,accumulator) 一样
        // 第三个参数 combiner 函数，则是将每个线程的执行结果当成一个新的流，然后使用第一个方法 reduce(accumulator) 流程进行规约
        // 目前暂认为所有的线程完成第一部之后进行乘积，求最终结果
        BigInteger v3 = list.parallelStream().reduce(BigInteger.valueOf(0),
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1.subtract(x2);
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1.multiply(x2);
                });

        // 结果：479001600 * 1295295050649600 = 620448401733239439360000
        System.out.println(v3);
    }
}
