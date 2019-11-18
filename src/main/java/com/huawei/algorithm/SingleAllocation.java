package com.huawei.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Algorithm assumes hosts and VMs share the same CPU/RAM ratio.
 */
public class SingleAllocation {

    public Allocation allocate(Machine[]hosts, Machine[] vms) {
        // group VMs by CPU
        Map<Integer, List<Machine>> hostsByCpuLog = new HashMap<>();
        for (Machine m: vms) {
            int a = log(m.cpu);

            List<Machine> list = hostsByCpuLog.get(a);
            if (list == null) {
                list = new ArrayList<>();
                hostsByCpuLog.put(a, list);
            }

            list.add(m);
        }
    }

    private String[][] sort(Machine[] machines) {
        // r[a] is array of machine ID with CPU==2^a.

        
    }

    private int log(int cpu) {
        // no time to write proper log_2
        switch (cpu) {
            case 1: {
                return 0;
            }

            case 2: {
                return 1;
            }

            case 4: {
                return 2;
            }

            case 8: {
                return 3;
            }

            case 16: {
                return 4;
            }

            case 32: {
                return 5;
            }
        }

        throw new IllegalArgumentException("Unexpected VM CPU value: " + cpu);
    }
}
