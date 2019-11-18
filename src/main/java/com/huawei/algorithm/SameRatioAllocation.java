package com.huawei.algorithm;

import java.util.*;

/**
 * Algorithm assumes hosts and VMs share the same CPU/RAM ratio.
 */
public class SameRatioAllocation {
    private List<Machine> unallocatedMachines = new ArrayList<>();

    // hosts by remaining CPU capacity
    // host[u] is a list of all hosts with capacity u
    // probably, should use a better data structure
    // for now, use it
    private HostGroup[] hostGroups;

    // host[a] is a group sharing CPU = 2^a, 5 <= a <= 6
    // vms[a]  is a group sharing CPU = 2^a, 1 <= a <= 5
    Allocation allocate(MachineGroup[] hosts, MachineGroup[] vms) {
        // start from larger VMs
        // to allocation VM find host with the least fitting capacity
        // allocate VM to the chosen host
        // adjust host capacity
        // remove VM from the list of VMs-to-be-allocated

        // in case VM cannot be allocated (no host with sufficient capacity found)
        // add VM to the list of unallocated VMs

        // init host groups
        hostGroups = new HostGroup[65];
        for (int cpu = 0; cpu <= 64; cpu += 1) {
            hostGroups[cpu] = new HostGroup();
        }

        // put hosts to host groups
        for (MachineGroup mg: hosts) {
            HostGroup hg = hostGroups[mg.cpu];

            for (String id: mg.ids) {
                Host h = new Host(id, mg.cpu, mg.ram);
                hg.stack.add(h);
            }
        }

        // init unallocated list
        this.unallocatedMachines = new ArrayList<>();

        // start allocation from larger VMs
        for (int a = Factorization.MAX_A; a >= Factorization.MIN_A; a -= 1) {
            MachineGroup g = vms[a]; // guaranteed to be not null by Factorization algorithms, IDs could be empty
            for (String id: g.ids) {
                Machine m = new Machine(id, g.cpu, g.ram);
                allocate(m);
            }
        }

        Allocation allocation = new Allocation();
        allocation.unallocatedVMs = this.unallocatedMachines;
        allocation.hosts = collectHosts(hostGroups);
        return allocation;
    }

    private void allocate(Machine m) {
        Host theHost = pollHostCpuNotLess(m.cpu);

        if (theHost != null) {
            allocateTo(m, theHost);
        } else{
            unallocatedMachines.add(m);
        }
    }

    // find host with least fitting capacity
    private Host pollHostCpuNotLess(int requestedCpu) {
        // Note on performance: probably, should use a better structure. For now, use linear search.
        for (int cpu = requestedCpu; cpu <= 64; cpu += 1) {
            HostGroup hg = hostGroups[cpu];
            if (hg == null || hg.stack.isEmpty()) {
                continue;
            }

            return hg.stack.pop(); // use as a stack to retrieve first hosts that has been used partially
            // this reduces number of partially used hosts
        }

        return null; // not found
    }

    private void allocateTo(Machine m, Host host) {
        host.vms.add(m);

        host.remainingCPU -= m.cpu;
        host.remainingRAM -= m.ram;

        HostGroup g = hostGroups[host.remainingCPU];
        g.stack.push(host); // using stack to reuse partially filled hosts
    }

    private List<Host> collectHosts(HostGroup[] groups) {
        List<Host> out = new ArrayList<>();

        for (HostGroup g: groups) {
            if (g != null) {
                out.addAll(g.stack);
            }
        }

        return out;
    }
}
