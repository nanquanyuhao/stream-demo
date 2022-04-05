package net.nanquanyuhao;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 使用 Pattern.splitAsStream() 方法，将字符串分隔成流
 */
public class PatternTest {

    private static Pattern pattern = Pattern.compile(",");

    public static void main(String[] args) {

        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }
}
