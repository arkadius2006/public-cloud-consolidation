package com.huawei;

import com.huawei.algorithm.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Test
    public void testCount() {
        Generator g = new Generator(10, 20);
        Cloud config = g.generate();

        Assert.assertEquals(10, config.hosts.size());
        Assert.assertEquals(20, config.vms.size());
        Assert.assertEquals(20, config.locations.size());

        Map<String, Machine> vmsById = new HashMap<>();
        for (Machine m: config.vms) {
            vmsById.put(m.id, m);
        }

        Map<String, Machine> hostsById = new HashMap<>();
        for (Machine m: config.hosts) {
            hostsById.put(m.id, m);
        }

        for (Map.Entry<String, String> e: config.locations.entrySet()) {
            String vmID = e.getKey();
            String hostID = e.getValue();

            Assert.assertTrue(vmsById.containsKey(vmID));
            Assert.assertTrue(hostsById.containsKey(hostID));
        }
    }
}
