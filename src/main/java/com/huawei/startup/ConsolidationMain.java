package com.huawei.startup;

import com.huawei.Cloud;
import com.huawei.algorithm.Allocation;
import com.huawei.algorithm.Consolidation;
import com.huawei.algorithm.Generator;
import com.huawei.algorithm.Host;

// for testing purposes
public class ConsolidationMain {

    public static void main(String[] args) {
        // generate
        Cloud cloud = new Generator(100, 200).generate();

        // consolidate
        Allocation allocation = new Consolidation().allocate(cloud.hosts, cloud.vms);

        System.out.println("Unallocated VMs: " + allocation.unallocatedVMs.size());

        // look inside
        long usedCpu = 0;
        long freeCpu = 0;
        long totalCpu = 0;
        int usedHostsCount = 0;
        // todo
    }
}
