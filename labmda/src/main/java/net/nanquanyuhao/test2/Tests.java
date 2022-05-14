package net.nanquanyuhao.test2;

/**
 * 无参数有返回值测试
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
            public String doSome() {
                return "匿名内部类";
            }
        };
        System.out.println(some.doSome());
    }

    public static void test02() {

        Some some = () -> {
            System.out.println("使用Labmda实现");
            return "Labmda";
        };
        System.out.println(some.doSome());
    }

    public static void test03() {

        Some some = () -> "Labmda";
        System.out.println(some.doSome());
    }
}

