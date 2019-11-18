package com.huawei.startup;

import com.huawei.Cloud;
import com.huawei.algorithm.Allocation;
import com.huawei.algorithm.Consolidation;
import com.huawei.algorithm.Generator;

// for testing purposes
public class ConsolidationMain {

    public static void main(String[] args) {
        // generate
        Cloud cloud = new Generator(100, 200).generate();

        // consolidate
        Allocation allocation = new Consolidation().allocate(cloud.hosts, cloud.vms);

        System.out.println("Unallocated VMs: " + allocation.unallocatedVMs.size());
    }
}
