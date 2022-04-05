package net.nanquanyuhao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 使用 stream() 及 parallelStream() 方法创建
 */
public class ListTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        //获取一个顺序流
        Stream<String> stream = list.stream();
        System.out.println(stream);

        //获取一个并行流
        Stream<String> parallelStream = list.parallelStream();
        System.out.println(parallelStream);
    }
}
