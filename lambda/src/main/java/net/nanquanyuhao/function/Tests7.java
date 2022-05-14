package net.nanquanyuhao.function;

import net.nanquanyuhao.Student;
import net.nanquanyuhao.StudentComparator;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * BinaryOperator 测试
 * 输入两个数据并返回一个数据，类型一致
 */
public class Tests7 {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    public static void test01() {

        BinaryOperator<Integer> bo = (x, y) -> x * y;
        System.out.println(bo.apply(3, 5)); // 15
    }

    public static void test02() {

        BinaryOperator<Integer> bo = (x, y) -> x * y;
        Function<Integer, String> up = n -> "结果为：" + n;
        // 将(3, 5) 应用于 bo 上，再将 bo 的运算结果作为 up 的参数进行计算
        System.out.println(bo.andThen(up).apply(3, 5)); // 结果为：15
    }

    public static void test03() {

        Student student3 = new Student("张三", 23);
        Student student4 = new Student("李四", 24);

        StudentComparator studentComparator = new StudentComparator();
        Student minStu = BinaryOperator.minBy(studentComparator).apply(student3, student4);
        Student maxStu = BinaryOperator.maxBy(studentComparator).apply(student3, student4);

        System.out.println(minStu); // student3
        System.out.println(maxStu); // student4
    }
}
