package net.nanquanyuhao.function;

import java.util.function.Consumer;

/**
 * Consumer 测试
 * 仅消费一个数据没有返回值
 */
public class Tests2 {

    public static void main(String[] args) {
        test01();
        test02();
    }

    public static void test01() {

        // 相当于实现有参数无返回值的接口
        Consumer<String> con = str -> System.out.println("Hello, " + str);
        con.accept("Tom");
    }

    public static void test02() {

        Consumer<Integer> con1 = n -> System.out.println(n * 2);
        Consumer<Integer> con2 = n -> System.out.println(n * n);
        con1.andThen(con2).accept(5);
    }
}
