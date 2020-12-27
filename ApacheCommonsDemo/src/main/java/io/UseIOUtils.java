package io;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class UseIOUtils {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            in = new URL("https://commons.apache.org").openStream();
            System.out.println(IOUtils.toString(in, Charset.forName("utf-8")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(in);
        }
    }
}
