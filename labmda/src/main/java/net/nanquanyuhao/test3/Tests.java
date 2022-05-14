package net.nanquanyuhao.test3;

/**
 * 有无参数有返回值测试
 */
public class Tests {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    public static void test01() {

        Some some = new Some() {
            @Override
            public String doSome(String a, int b) {
                return a + b;
            }
        };
        System.out.println(some.doSome("Hello, ", 2019));
    }

    public static void test02() {

        Some some = (str, n) -> str + n;
        System.out.println(some.doSome("Hello, ", 2020));
    }

    public static void test03() {

        Some some = (str, n) -> {
            System.out.println("使用Labmda实现");
            return str + n;
        };
        System.out.println(some.doSome("Hello, ", 2021));
    }
}

