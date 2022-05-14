package net.nanquanyuhao.function;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * 断言测试
 */
public class Tests1 {

    public static void main(String[] args) {

        test01();
        test02();
        test03();
    }

    public static void test01() {

        Predicate<Integer> pre = i -> i > 8;
        IntPredicate intPre = i -> i < 3;
        DoublePredicate doublePredicate = n -> n < 5;

        System.out.println(pre.test(9)); // true
        System.out.println(pre.test(7)); // false

        System.out.println(intPre.test(9)); // false
        System.out.println(intPre.test(2)); // true

        System.out.println(doublePredicate.test(2)); // true
        System.out.println(doublePredicate.test(6)); // false
    }

    public static void test02() {

        Predicate<Integer> gt8 = i -> i > 8;
        Predicate<Integer> lt3 = i -> i < 3;

        System.out.println(gt8.and(lt3).test(9)); // false
        System.out.println(gt8.or(lt3).test(9)); // true
        System.out.println(gt8.negate().test(9)); // false
    }

    public static void test03() {

        System.out.println(Predicate.isEqual("Hello").test("hello")); // false
        System.out.println(Predicate.isEqual("Hello").test("Hello")); // true
    }
}
