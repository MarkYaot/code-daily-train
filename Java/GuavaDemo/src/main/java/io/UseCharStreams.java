package io;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class UseCharStreams {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            in = new URL("https://www.baidu.com").openStream();
            byte[] bytes = new byte[10];
            
            //读取字符流并打印
            CharStreams.readLines(new InputStreamReader(in));
            System.out.println(Arrays.toString(bytes));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}
