package com.huawei;

import com.huawei.io.JsonConfigurationWriter;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

public class JsonConfigWriterTest {

    @Test
    public void test() throws IOException  {
        CloudConfiguration config = new Generator(1, 1).generate();
        StringWriter sw = new StringWriter();
        new JsonConfigurationWriter().write(config, sw);

        String s = sw.toString();
        System.out.println(s);
    }
}
