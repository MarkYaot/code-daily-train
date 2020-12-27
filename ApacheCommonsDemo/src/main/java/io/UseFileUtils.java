package io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class UseFileUtils {
    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    public static void main(String[] args) {
        File file = new File("C:/tmp/test.properties");
        try {
            if (!file.exists()) {
                FileUtils.writeStringToFile(file, "aaa", DEFAULT_CHARSET);
            }
            System.out.println(FileUtils.readFileToString(file, DEFAULT_CHARSET));
            FileUtils.writeStringToFile(file, "aaa", DEFAULT_CHARSET, true);
            System.out.print(FileUtils.readFileToString(file, DEFAULT_CHARSET));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteQuietly(file);
        }
    }
}
