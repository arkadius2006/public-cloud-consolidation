package com.huawei;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class GeneratorTest {

    @Test
    public void testCount() {
        Generator g = new Generator(10, 30);
        CloudConfiguration config = g.generate();

        Assert.assertEquals(10, config.hostConfigurations.size());
        Assert.assertEquals(30, config.vmConfigurations.size());
        Assert.assertEquals(30, config.locations.size());

        for (Map.Entry<String, String> e: config.locations.entrySet()) {
            String vmID = e.getKey();
            String hostID = e.getValue();

            Assert.assertTrue(config.vmConfigurations.containsKey(vmID));
            Assert.assertTrue(config.hostConfigurations.containsKey(hostID));
        }
    }
}
