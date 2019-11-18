package com.huawei.algorithm;

import java.util.*;

/**
 * Algorithm assumes hosts and VMs share the same CPU/RAM ratio.
 *
 * Additional assumptions:
 * host[a] is a group sharing CPU = 2^a, 5 <= a <= 6
 * vms[a]  is a group sharing CPU = 2^a, 1 <= a <= 5
 */

public class UniConsolidationAlgorithm {
    private List<Machine> unallocatedMachines = new ArrayList<>();
    private HostPool hostPool = new ArrayHostPool();

    Allocation allocate(List<MachineGroup> hostGroups, List<MachineGroup> guestGroups) {
        // start from larger VMs
        // to allocation VM find host with the least fitting capacity
        // allocate VM to the chosen host
        // adjust host capacity
        // remove VM from the list of VMs-to-be-allocated

        // in case VM cannot be allocated (no host with sufficient capacity found)
        // add VM to the list of unallocated VMs

        // add hosts to pool
        for (MachineGroup group: hostGroups) {
            for (String id: group.ids) {
                Host h = new Host(id, group.cpu, group.ram);
                hostPool.add(h);
            }
        }

        // init unallocated list
        this.unallocatedMachines = new ArrayList<>();

        // sort guest groups desc
        guestGroups.sort((o1, o2) -> Integer.compare(o2.cpu, o1.cpu));

        // start allocation from larger VMs
        for (MachineGroup g: guestGroups) {
            for (String id: g.ids) {
                Machine m = new Machine(id, g.cpu, g.ram);
                allocate(m);
            }
        }

        Allocation allocation = new Allocation();
        allocation.unallocatedVMs = this.unallocatedMachines;
        allocation.hosts = hostPool.toList();
        return allocation;
    }

    private void allocate(Machine m) {
        Host theHost = hostPool.poll(m.cpu);

        if (theHost != null) {
            allocateTo(m, theHost);
        } else{
            unallocatedMachines.add(m);
        }
    }

    private void allocateTo(Machine m, Host host) {
        host.vms.add(m);

        host.remainingCPU -= m.cpu;
        host.remainingRAM -= m.ram;

        // return host to pool
        hostPool.add(host);
    }
}
