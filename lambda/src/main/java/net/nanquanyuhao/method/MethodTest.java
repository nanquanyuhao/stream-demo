package net.nanquanyuhao.method;

import net.nanquanyuhao.Student;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Lambda 方法引用
 */
public class MethodTest {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
        test07();
    }

    public static void test01() {

        Student student = new Student("张三");
        System.out.println(student.play(5));
        student.study("webflux");
    }

    /**
     * Lambda 静态方法引用
     * 类名::静态方法名
     */
    public static void test02() {
        // sleeping() 方法只有一个输入，没有输出，符合函数式接口 Consumer 的定义
        Consumer<Integer> con = Student::sleeping;
        con.accept(8); // 相当于 Student.sleeping(8);
    }

    /**
     * Lambda 实例方法引用
     * 实例名::实例方法名
     */
    public static void test03() {

        Student student = new Student("李四");
        // play() 方法只有一个输入，且有输出，符合函数式接口 Function 的定义
        Function<Integer, String> fun = student::play;
        System.out.println(fun.apply(5)); // 相当于 student.play(5);
    }

    /**
     * Lambda 实例方法引用
     * 类名::实例方法名
     */
    public static void test04() {

        Student student = new Student("李四");
        // play() 方法两个输入，且有输出，符合函数式接口 BiFunction 的定义
        // 写法中，由于需要传入实例，所以直接使用类名发起调用即可
        BiFunction<Student, Integer, String> bf = Student::play;
        System.out.println(bf.apply(student, 5)); // 相当于 student.play(5);
    }

    /**
     * Lambda 实例方法引用
     * 实例名::实例方法名
     */
    public static void test05() {

        Student student = new Student("李四");
        // study() 方法只有一个输入，没有输出，符合函数式接口 Consumer 的定义
        Consumer<String> con = student::study;
        con.accept("WebFlux"); // 相当于 student.study("WebFlux");
    }

    /**
     * Lambda 无参构造器引用
     * 类名::new
     */
    public static void test06() {

        // 无参构造器没有输入，但有输出，符合函数式接口 Supplier 的定义
        Supplier<Student> supp = Student::new;
        System.out.println(supp.get()); // 相当于 new Student();
    }

    /**
     * Lambda 带参构造器引用
     * 类名::new
     */
    public static void test07() {

        // 带参构造器仅有一个输入，且有输出，符合函数式接口 Supplier 的定义
        Function<String, Student> fun = Student::new;
        System.out.println(fun.apply("王五")); // 相当于 new Student("王五");
    }
}
