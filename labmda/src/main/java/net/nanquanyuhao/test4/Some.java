package net.nanquanyuhao.test4;

@FunctionalInterface
public interface Some {

    /**
     * 有参数有返回值
     */
    String doSome(String a, int b);

    default void doOther(String a, int b) {
        System.out.println("执行默认方法 doOther() -" + a + b);
    }
}
