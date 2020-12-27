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
            byteSink.write("aaa".getBytes());
            System.out.println(Arrays.toString(byteSource.read()));
            System.out.println(charSource.read());
            charSink.write("bbb");
            System.out.print(charSource.read());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}
