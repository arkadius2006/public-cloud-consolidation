package com.huawei.algorithm;


import com.huawei.Machine;
import com.huawei.MachineGroup;

import java.util.Arrays;
import java.util.List;

public class Consolidation {

    public Allocation allocate(List<Machine> hosts, List<Machine> vms) {

        // sort hosts by CPU/RAM ratio
        MachineGroup[][] hostGroups = new Factorization().sort(hosts);
        MachineGroup[][] vmGroups = new Factorization().sort(vms);

        // do allocation for each ratio independently
        Allocation sumAllocation = new Allocation();
        for (int p = Factorization.MIN_P; p <= Factorization.MAX_P; p += 1) {
            Allocation singleRatioAllocation = new MonoConsolidation().allocate(
                    Arrays.asList(hostGroups[p]),
                    Arrays.asList(vmGroups[p]));

            sumAllocation.hosts.addAll(singleRatioAllocation.hosts);
            sumAllocation.unallocatedVMs.addAll(singleRatioAllocation.unallocatedVMs);
        }

        // todo need to handle cross-ratio allocation

        return sumAllocation;
    }
}
