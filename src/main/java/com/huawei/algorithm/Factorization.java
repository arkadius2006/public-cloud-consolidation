package com.huawei.algorithm;

import java.util.ArrayList;
import java.util.List;

// Sort machines by RAM/CPU ratio. Accepted ratios are: 2^p where p = 1, 2, 3.
public class Factorization {
    static final int MIN_P = 1;
    static final int MAX_P = 3;

    public Group[] factorize(List<Machine> machines) {
        Group[] groups = new Group[MAX_P + 1];
        for (int p = 0; p <= MAX_P; p += 1) {
            Group g = groups[p];
            g.p = p;
            g.machines = new ArrayList<>();
        }

        for (Machine c: machines) {
            int r = getRatio(c.ram, c.cpu);
            int p = logRatio(r);
            if (p > MAX_P || p < MIN_P) {
                throw new IllegalArgumentException("Illegal ratio logarithm: " + p);
            }

            Group g = groups[p];
            g.machines.add(c);
        }

        return groups;
    }

    private int getRatio(int ram, int cpu) {
        int r = ram/cpu;

        if (ram != cpu * r) {
            throw new IllegalArgumentException("Illegal CPU/RAM configuration: " + cpu + "/" + ram);
        }

        return r;
    }

    private int logRatio(int r) {
        switch (r) {
            case 2: {
                return 1;
            }

            case 4: {
                return 2;
            }

            case 8: {
                return 3;
            }
        }

        throw new IllegalArgumentException("Illegal RAM/CPU ratio: " + r);
    }
}
