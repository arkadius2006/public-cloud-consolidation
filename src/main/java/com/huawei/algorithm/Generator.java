package com.huawei.algorithm;

import com.huawei.Cloud;
import com.huawei.Machine;

import java.util.*;

public class Generator {
    private final int hostCount;
    private final int vmCount;
    private final Random random;

    public Generator(int hostCount, int vmCount) {
        if (hostCount <= 0) {
            throw new IllegalArgumentException("hosts <= 0");
        }

        if (vmCount <= 0) {
            throw new IllegalArgumentException("vms <= 0");
        }

        this.hostCount = hostCount;
        this.vmCount = vmCount;
        this.random = new Random();
    }

    public Cloud generate() {

        // 1. generate hosts one by one
        List<Machine> hosts = new ArrayList<>();
        for (int i = 0; i < hostCount; i += 1) {
            Machine c = generateHost(i);
            hosts.add(c);
        }

        // 2. generate VMs one by one
        List<Machine> vms = new ArrayList<>();
        for (int j = 0; j < vmCount; j += 1) {
            Machine c = generateVm(j);
            vms.add(c);
        }

        // 3. Allocate "inefficiently". Scan hosts from left to right to find the first fitting.
        // This algorithm is inefficient, it is used to model inefficient allocation.
        int[] remainingCpu = new int[hostCount];
        int[] remainingRam = new int[hostCount];
        for (int i = 0; i < hostCount; i += 1) {
            remainingCpu[i] = hosts.get(i).cpu;
            remainingRam[i] = hosts.get(i).ram;
        }

        Map<String, String> locations = new HashMap<>();
        for (int j = 0; j < vmCount; j += 1) {
            Machine vm = vms.get(j);

            // allocate machine j
            boolean allocated = false;
            for (int i = 0; i < hostCount; i += 1) {
                Machine host = hosts.get(i);

                if (vm.cpu <= remainingCpu[i] && vm.ram <= remainingRam[i]) {
                    locations.put(vm.id, host.id);
                    remainingCpu[i] -= vm.cpu;
                    remainingRam[i] -= vm.ram;
                    allocated = true;
                    break;
                }
            }

            if (!allocated) {
                throw new IllegalArgumentException("Failed to generate conf for given params"); // with some probability this will happen for given generation params
            }
        }

        Cloud cc = new Cloud();
        cc.hosts = hosts;
        cc.vms = vms;
        cc.locations = locations;
        return cc;
    }

    private Machine generateHost(int seq) {
        int a = random(5, 7);
        int p = random(1, 4);
        String id = String.format("host-%d", seq);

        return makeMachine(a, p, id);
    }

    private Machine generateVm(int seq) {
        int a = random(1, 6);
        int p = random(1, 4);
        String id = String.format("vm-%d", seq);

        return makeMachine(a, p, id);
    }

    private Machine makeMachine(int a, int p, String id) {
        Machine c = new Machine();
        c.cpu = 1 << a;
        c.ram = 1 << (a + p);
        c.id = id;
        return c;
    }

    /**
     * lower - inclusive
     * upper - exclusive
     */
    private int random(int lower, int upper) {
        int r = random.nextInt(upper - lower);
        return r + lower;
    }
}
