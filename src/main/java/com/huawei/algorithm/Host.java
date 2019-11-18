package com.huawei.algorithm;

import com.huawei.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Host that has some VM already allocated into it.
 */
public class Host {
    String id;
    List<Machine> vms;
    int remainingCPU;
    int remainingRAM;

    Host() {
        vms = new ArrayList<>();
    }

    Host(String id, int remainingCPU, int remainingRAM) {
        this();
        this.id = id;
        this.remainingCPU = remainingCPU;
        this.remainingRAM = remainingRAM;
    }

}
