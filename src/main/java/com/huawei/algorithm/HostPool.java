package com.huawei.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public interface HostPool {

    /**
     * Adds host to pool.
     */
    void add(Host host);

    /**
     * Finds and retrieves host with least fitting CPU capacity.
     * Returns null if no such host found.
     */
    Host poll(int requestedCpu);

    /**
     * Collects all hosts in pool to list.
     */
    List<Host> toList();
}

class ArrayHostPool implements HostPool {
    static final int MIN_CPU = 0;
    static final int MAX_CPU = 64;

    // hosts by remaining CPU capacity
    // host[u] is a list of all hosts with capacity u
    // probably, should use a better data structure
    private HostGroup[] groups;

    ArrayHostPool() {
        groups = new HostGroup[MAX_CPU + 1];
        for (int cpu = 0; cpu <= MAX_CPU; cpu += 1) {
            groups[cpu] = new HostGroup();
        }
    }

    @Override
    public void add(Host host) {
        int cpu = host.remainingCPU;

        if (cpu < MIN_CPU || cpu > MAX_CPU) {
            throw new IllegalArgumentException("Illegal cpu: " + host.remainingCPU);
        }

        HostGroup g = groups[cpu];
        g.stack.push(host); // using stack to reuse partially filled hosts
    }

    @Override
    public Host poll(int requestedCpu) {
        for (int cpu = requestedCpu; cpu <= 64; cpu += 1) {
            HostGroup group = groups[cpu];
            if (group == null || group.stack.isEmpty()) {
                continue;
            }

            return group.stack.pop(); // use as a stack to retrieve first hosts that has been used partially
            // this reduces number of partially used hosts
        }

        return null; // not found
    }

    @Override
    public List<Host> toList() {
        List<Host> out = new ArrayList<>();

        for (HostGroup g: groups) {
            if (g != null) {
                out.addAll(g.stack);
            }
        }

        return out;
    }
}

class HostGroup {
    Deque<Host> stack = new ArrayDeque<>();
}

