package com.huawei.algorithm;

/**
 * Both host and VM machines.
 */
public class Machine {
    String id;
    int cpu;
    int ram;

    public Machine() {

    }

    public Machine(String id, int cpu, int ram) {
        this.id = id;
        this.cpu = cpu;
        this.ram = ram;
    }
}
