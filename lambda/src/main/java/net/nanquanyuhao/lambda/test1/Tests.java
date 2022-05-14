package net.nanquanyuhao.lambda.test1;

/**
 * 无参数无返回值测试
 */
public class Tests {

    public static void main(String[] args) {
        test01();
        test02();
    }

    public static void test01() {

        Some some = new Some() {
            @Override
            public void doSome() {
                System.out.println("使用匿名内部类实现");
            }
        };
        some.doSome();
    }

    public static void test02() {

        Some some = () -> {
            System.out.println("使用 Lambda 实现");
        };
        some.doSome();
    }
}

