package net.nanquanyuhao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 映射测试
 */
public class MapTest {

    public static void main(String[] args) {

        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
    }

    public static void test01() {

        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        // map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个（1-1）新的元素
        // 将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        // 结果：abc  123
        s1.forEach(System.out::println);

        // 将字符串全部大写输出
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        // 将一个 Stream 的每个元素映射成另一个元素并生成一个新的 Stream，可以将一种元素类型转换成另一种元素类型
        String[] inputs = {"Bob, 15", "Alice, 20", "Time, 25", "Lily, 30"};
        Stream<String> names = Arrays.stream(inputs);
        Stream<Student> students = names.map(s -> {
            int n = s.indexOf(',');
            String name = s.substring(0, n);
            int age = Integer.valueOf(s.substring(n + 2));
            return new Student(name, age);
        });
        students.forEach(System.out::println);

        // flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流（可能存在 1-N）
        Stream<String> s3 = list.stream().flatMap(s -> {
            // 将每个元素转换成一个 stream
            String[] split = s.split(",");
            // 使用 Arrays 中的 stream() 方法，将数组转成流
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        // 结果：a b c 1 2 3
        s3.forEach(System.out::println);
    }

    /**
     * 在所有操作均为无状态操作时
     * <p>
     * 流中的元素对于操作的执行并非是将流中的所有元素按照顺序先执行完一个操作，再让所有元素执行第二个操作
     * 是逐个拿出元素，将所有操作执行完成后，再拿出一个元素将所有操作再执行完
     */
    public static void test02() {

        String words = "Beijing welcome you I love China";
        Stream.of(words.split(" ")) // 当前流中的元素为各个单词
                .peek(System.out::print)
                .map(word -> word.length()) // 当前流中的元素为各个单词的长度
                // peek 操作是中间操作，forEach 是终止操作，参数都是消费者方法（只进不出）
                .forEach(System.out::println);
    }

    public static void test03() {

        IntStream.range(1, 10)
                .mapToObj(i -> "No." + i)
                .forEach(System.out::println);
    }

    public static void test04() {

        String[] nums = {"111", "222", "333"};
        Arrays.stream(nums) // "111", "222", "333"
                .mapToInt(Integer::valueOf) // 111, 222, 333
                .forEach(System.out::println);
    }

    public static void test05() {

        String words = "Beijing welcome you I love China";
        Stream.of(words.split(" ")) // 当前流中的元素为各个单词
                .flatMap(word -> word.chars().boxed()) // flatMap 中的参数为 Function，且要求返回类型为 Stream
                .forEach(ch -> System.out.println((char) ch.intValue()));
    }

    public static void test06() {

        String words = "Beijing welcome you I love China";
        Stream.of(words.split(" ")) // 当前流中的元素为各个单词
                .flatMap(word -> Stream.of(word.split(""))) // 最终形成的流中的元素为各个单词的字母
                .forEach(System.out::println);
    }
}
