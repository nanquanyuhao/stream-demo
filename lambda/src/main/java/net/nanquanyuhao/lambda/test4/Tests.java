package net.nanquanyuhao.lambda.test4;

/**
 * 有无参数有返回值测试
 */
public class Tests {

    public static void main(String[] args) {
        test01();
    }

    public static void test01() {

        Some some = (a, b) -> a + b;
        System.out.println(some.doSome("Hello, ", 2020));

        some.doOther("Hello, ", 2019);
    }

}