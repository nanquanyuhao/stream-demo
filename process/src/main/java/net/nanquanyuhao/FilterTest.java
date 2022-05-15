package net.nanquanyuhao;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 筛选与切片
 */
public class FilterTest {

    public static void main(String[] args) {

        test01();
        test02();
        test03();
    }

    public static void test01() {
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

        Stream<BigInteger> natural = Stream.generate(new NaturalSupplier());
        // 过滤条件为 n 是否可以被 2 整除，留下奇数 20 个
        Stream<BigInteger> odd = natural.filter(
                // 取余有专有公式 mod
                n -> n.mod(BigInteger.valueOf(2)).intValue() == 1);
        odd.limit(20).forEach(System.out::println);


        // 字符串处理，排除掉无实际内容的字符串，并输出调整过滤后的结果
        String[] array = {"Java", " Python ", " ", null, "\n\n", " Ruby "};
        Stream<String> normalized = Arrays.stream(array).filter(s -> s != null && !s.trim().isEmpty())
                .map(s -> s.trim());
        normalized.forEach(System.out::println);
    }

    public static void test02() {

        String words = "Beijing welcome you I love China";
        Stream.of(words.split(" ")) // 当前流中的元素为各个单词
                // 当过滤条件为 true 时，当前元素会被保留在流中，否则从流中删除
                .filter(word -> word.length() > 4)
                .forEach(System.out::println);
    }

    public static void test03() {

        String words = "Beijing welcome you I love China";
        Stream.of(words.split(" ")) // 当前流中的元素为各个单词
                .flatMap(word -> Stream.of(word.split(""))) // 最终形成的流中的元素为各个单词的字母
                .distinct() // 去除重复的字符
                .sorted()
                .skip(4)  // 指定跳过（去除）4 个元素
                .forEach(System.out::print);
    }
}
