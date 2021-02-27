package io;

import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class UseByteStreams {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            in = new URL("https://www.baidu.com").openStream();
            byte[] bytes = new byte[10];

            //读取字节流并打印
            ByteStreams.readFully(in, bytes);
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
