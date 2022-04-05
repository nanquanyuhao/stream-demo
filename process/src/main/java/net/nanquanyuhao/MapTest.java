package net.nanquanyuhao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 映射测试
 */
public class MapTest {

    public static void main(String[] args) {

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
}
