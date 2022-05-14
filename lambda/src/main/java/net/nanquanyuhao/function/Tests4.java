package net.nanquanyuhao.function;

import java.util.function.Function;

/**
 * Function 测试
 * 消费一个数据并产生一个数据，类型不一致
 */
public class Tests4 {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    public static void test01() {
        Function<Integer, String> fun = n -> "我爱你，" + n;
        System.out.println(fun.apply(2019));
    }

    public static void test02() {
        Function<Integer, Integer> fun1 = x -> x * 2;
        Function<Integer, Integer> fun2 = x -> x * x;

        // 先将 5 作为 fun1 的参数，计算结果为 10
        // 再将 fun1 的计算结果 10，作为 fun2 的参数在计算
        System.out.println(fun1.andThen(fun2).apply(5)); // 100

        // 先将 5 作为 fun2 的参数，计算结果为 25
        // 再将 fun2 的计算结果 25，作为 fun1 的参数在计算
        System.out.println(fun1.compose(fun2).apply(5)); // 50
    }

    /**
     * 输入内容即输出内容
     */
    public static void test03() {
        System.out.println(Function.identity().apply(5)); // 5
        System.out.println(Function.identity().apply(3 * 8)); // 24
    }
}
