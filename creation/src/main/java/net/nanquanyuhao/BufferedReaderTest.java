package net.nanquanyuhao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

/**
 * 使用 BufferedReader.lines() 方法，将每行内容转成流
 */
public class BufferedReaderTest {

    private static final String FILE_NAME =
            "D:\\code\\github\\stream-demo\\creation\\src\\main\\java\\net\\nanquanyuhao\\BufferedReaderTest.java";

    public static void main(String[] args) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);
    }
}
