package net.nanquanyuhao.function;

import java.util.function.Supplier;

/**
 * Supplier 测试
 * 返回数据且不需要消费数据
 */
public class Tests3 {

    public static void main(String[] args) {

        // 相当于实现无参数有返回值的接口
        Supplier<String> supp = () -> "Lambda";
        System.out.println(supp.get());
    }
}
