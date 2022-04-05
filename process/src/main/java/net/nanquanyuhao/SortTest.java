package net.nanquanyuhao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 排序测试，排序对象需要实现了 Compareable 接口
 */
public class SortTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("aa", "ff", "dd");
        // String 类自身已实现 Compareable 接口，结果：aa dd ff
        list.stream().sorted().forEach(System.out::println);

        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        Student s3 = new Student("aa", 30);
        Student s4 = new Student("dd", 40);
        List<Student> studentList = Arrays.asList(s1, s2, s3, s4);

        //自定义排序：先按姓名升序，姓名相同则按年龄升序
        studentList.stream().sorted(
                (o1, o2) -> {
                    if (o1.getName().equals(o2.getName())) {
                        // 年龄升序，返回值为对比的两对象的差值
                        return o1.getAge() - o2.getAge();
                    } else {
                        // 名字不一样的情况依照名称升序排
                        return o1.getName().compareTo(o2.getName());
                    }
                }
        ).forEach(System.out::println);
    }
}