package com.huawei.algorithm;

import java.util.List;

/**
 * Host that has some VM already allocated into it.
 */
public class Host {
    String id;
    List<Machine> vms;
    int remainingCPU;
    int remainingRAM;

}
