package net.nanquanyuhao;

import java.util.Arrays;
import java.util.List;

/**
 * 消费测试
 */
public class ConsumeTest {

    public static void main(String[] args) {

        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        List<Student> studentList = Arrays.asList(s1, s2);

        studentList.stream()
                // peek：如同于 map，能得到流中的每一个元素
                // map 接收的是一个 Function 表达式，有返回值；而peek接收的是 Consumer 表达式，没有返回值。
                .peek(o -> o.setAge(100))
                .forEach(System.out::println);
    }
}
