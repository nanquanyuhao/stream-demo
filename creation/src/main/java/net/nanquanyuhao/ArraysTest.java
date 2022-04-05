package net.nanquanyuhao;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 使用 Arrays 中的 stream() 方法，将数组转成流
 */
public class ArraysTest {

    public static void main(String[] args) {

        Integer[] nums = new Integer[10];
        Stream<Integer> stream = Arrays.stream(nums);

        System.out.println(stream);
    }
}
