package jdev.tracker.consume;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

/**
 * Created by artem on 11.02.24.
 */
public class RestConsumer {
    public static void main(String... args) throws IOException {


        String response = IOUtils.toString(new URL("http://localhost:8080/relay"), "UTF8");
        System.out.println(response);
    }

}
