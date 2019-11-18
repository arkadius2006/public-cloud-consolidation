package com.huawei.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Allocation {
    public List<Host> hosts;
    public List<Machine> unallocatedVMs;

    Allocation() {
        hosts = new ArrayList<>();
        unallocatedVMs = new ArrayList<>();
    }
}
