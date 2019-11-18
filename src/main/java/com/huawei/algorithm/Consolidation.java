package com.huawei.algorithm;


import java.util.List;

public class Consolidation {

    public Allocation consolidate(List<Machine> hosts, List<Machine> vms) {

        // sort hosts by CPU/RAM ratio
        Group[][] hostGroups = new Factorization().sort(hosts);
        Group[][] vmGroups = new Factorization().sort(vms);

        // do allocation for each group independently
        for (int p = Factorization.MIN_P; p <= Factorization.MAX_P; p += 1) {

        }

        throw new IllegalArgumentException("Not implemented");
    }
}
