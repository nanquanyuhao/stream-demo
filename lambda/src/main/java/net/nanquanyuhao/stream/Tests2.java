package net.nanquanyuhao.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 并行与串行
 */
public class Tests2 {

    public static void print(int i) {

        String name = Thread.currentThread().getName();
        System.out.println(i + " -- " + name);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printRed(int i) {

        String name = Thread.currentThread().getName();
        System.err.println(i + " -- " + name);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // test01();
        // test02();
        // test03();
        // test04();
        // test05();
        test06();
    }

    /**
     * 串行处理
     */
    public static void test01() {
        IntStream.range(1, 100)
                .peek(Tests2::print)
                .count();
    }

    /**
     * 并行处理
     */
    public static void test02() {
        IntStream.range(1, 100)
                .parallel()
                .peek(Tests2::print)
                .count();
    }

    /**
     * 串并行混合处理
     * 串并行执行效果为后者
     */
    public static void test03() {
        IntStream.range(1, 100)
                .parallel()
                .peek(Tests2::print)
                .sequential()
                .peek(Tests2::printRed)
                .count();
    }

    /**
     * 串并行混合处理
     * 串并行执行效果为后者
     */
    public static void test04() {
        IntStream.range(1, 100)
                .sequential()
                .peek(Tests2::print)
                .parallel()
                .peek(Tests2::printRed)
                .count();
    }

    /**
     * 修改默认线程池中的线程数量
     */
    public static void test05() {
        // 指定默认线程池中的数量为 32，其中包含指定的 31 个与 main 线程
        String key = "java.util.concurrent.ForkJoinPool.common.parallelism";
        System.setProperty(key, "31");

        IntStream.range(1, 100)
                .parallel()
                .peek(Tests2::print)
                .count();
    }

    public static void test06() {
        // 创建线程池，包含 4 个线程
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.submit(() -> IntStream.range(1, 100)
                .parallel()
                .peek(Tests2::print)
                .count());

        // wait()、notify()、notifyAll() 方法必须在同步方法或同步代码块中被调用（synchronized 修饰的方法或者代码块）
        // 哪个对象调用了这些方法，哪个对象就要充当同步锁
        synchronized (pool) {
            try {
                // main 线程被阻塞在了这里
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
