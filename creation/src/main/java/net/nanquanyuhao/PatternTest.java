package net.nanquanyuhao;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 使用 Pattern.splitAsStream() 方法，将字符串分隔成流
 */
public class PatternTest {

    private static Pattern pattern1 = Pattern.compile(",");

    /**
     * 此正则更好用，前后带空格的元素最终将中间内容进行匹配
     */
    private static Pattern pattern2 = Pattern.compile("\\s*\\,\\s*");

    public static void main(String[] args) {

        Stream<String> stringStream = pattern1.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);

        Stream<String> s = pattern2.splitAsStream("a, b, c, dd, E, ff");
        s.forEach(System.out::println);
    }
}
