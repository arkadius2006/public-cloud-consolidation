package com.huawei;

import com.huawei.startup.GenerationParams;
import com.huawei.startup.GenerationParamsParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenerationParamsParserTest {
    private GenerationParamsParser parser;

    @Before
    public void setUp() {
        parser = new GenerationParamsParser();
    }

    @Test
    public void test() {
        String line = "--hosts 1000 --vms 3000 --output abc.json";
        String[] args = line.split("\\s+");

        GenerationParams params = parser.parse(args);

        Assert.assertEquals(1000, params.hosts);
        Assert.assertEquals(3000, params.vms);
        Assert.assertEquals("abc.json", params.output);
    }
}
