package com.huawei.algorithm;

import java.util.ArrayList;
import java.util.List;

// Sort machines by RAM/CPU. Assumptions: CPU = 2^a, 1 <= a <= 5. RAM/CPU = 2^p, 1 <= p <= 3
class Factorization {

    MachineGroup[][] sort(List<Machine> machines) {
        MachineGroup[][] groups = new MachineGroup[MAX_P + 1][MAX_A + 1];
        for (int p = MIN_P;p <= MAX_P; p += 1) {
            for (int a = MIN_A; a <= MAX_A; a += 1) {
                MachineGroup g = new MachineGroup();
                g.ids = new ArrayList<>();

                g.cpu = 1 << a;
                g.ram = 1 << (a + p);

                groups[p][a] = g;
            }
        }

        for (Machine m: machines) {
            int a = log(m.cpu);
            int b = log(m.ram);
            int p = b - a;

            if (p < MIN_P || p > MAX_P) {
                throw new IllegalArgumentException("Illegal RAM/CPU ratio: " + m.ram + "/" + m.cpu);
            }

            if (a < MIN_A || a > MAX_A) {
                throw new IllegalArgumentException("Illegal CPU: " + m.cpu);
            }

            groups[p][a].ids.add(m.id);
        }

        return groups;
    }

    private int log(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x <= 0");
        }

        int p = 0;
        int y = 1;
        // y = 2^p
        while (p < 31) {
            if (x == y) {
                return p;
            }

            y = y << 1;
            p += 1;
        }

        throw new IllegalArgumentException("Number " + x + " is not a power of 2");
    }

    static final int MIN_P = 1;
    static final int MAX_P = 3;

    static final int MIN_A = 1;
    static final int MAX_A = 5;
}
