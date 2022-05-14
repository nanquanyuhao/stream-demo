package net.nanquanyuhao.function;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * BiFunction 测试
 * 输入两个数据并返回一个数据，类型可不一致
 */
public class Tests6 {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    public static void test01() {

        // 相当于实现有参数有返回值的接口
        BiFunction<Integer, Integer, String> biFun = (x, y) -> x + " : " + y;
        System.out.println(biFun.apply(3, 5)); // 3 : 5
    }

    public static void test02() {

        BiFunction<Integer, Integer, String> biFun = (x, y) -> x + " : " + y;
        UnaryOperator<String> up = str -> "键值对为：" + str;

        // 将(3, 5) 应用于 biFun 上，再将 biFun 的运算结果作为 up 的参数进行计算
        System.out.println(biFun.andThen(up).apply(3, 5)); // 键值对为：3 : 5
    }

    public static void test03() {

        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> x + y;
        Function<Integer, String> up = n -> "结果为：" + n;

        // 将(3, 5) 应用于 biFun 上，再将 biFun 的运算结果作为 up 的参数进行计算
        System.out.println(biFun.andThen(up).apply(3, 5)); // 结果为：8
    }
}
