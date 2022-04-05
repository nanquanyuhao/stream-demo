package net.nanquanyuhao;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * 使用 Stream 中的静态方法：of()、iterate()、generate()
 */
public class StaticTest {

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of( 1, 2, 3, 4, 5, 6);
        stream.forEach(System.out::println);

        // 取六个值，从零开始递增的等差数列
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);
        // 0 2 4 6 8 10
        stream2.forEach(System.out::println);

        // 取两个值，每个都是随机数，如果不限制个数的话可以表述无限序列
        Stream<Double> stream3 = Stream.generate(Math::random).limit(2);
        stream3.forEach(System.out::println);

        // 不加限制个数打印自然数会陷入死循环
        Stream<BigInteger> s = Stream.generate(new NaturalSupplier()).limit(100);
        s.forEach(System.out::println);
    }
}
