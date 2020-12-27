package io;

import com.google.common.io.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class UseSourceSink {
    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    public static void main(String[] args) {
        File file = new File("C:/tmp/test.properties");
        ByteSource byteSource = Files.asByteSource(file);
        ByteSink byteSink = Files.asByteSink(file);
        CharSource charSource = Files.asCharSource(file, DEFAULT_CHARSET);
        CharSink charSink = Files.asCharSink(file, DEFAULT_CHARSET, new FileWriteMode[]{FileWriteMode.APPEND});

        try {
            //使用字节流写入文件
            byteSink.write("aaa".getBytes());
            //使用字节流读取文件
            System.out.println(Arrays.toString(byteSource.read()));
            //使用字符流读取文件
            System.out.println(charSource.read());
            //使用字符流写入文件
            charSink.write("bbb");
            //使用字符流读取文件
            System.out.print(charSource.read());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
