package com.huawei.algorithm;

import java.util.ArrayList;
import java.util.List;

// Sort configurations by RAM/CPU ratio. Accepted ratios are: 2^p where p = 1, 2, 3.
public class RatioFactorization {
    static final int MAX_P = 3;

    public RatioGroup[] factorize(List<Configuration> configurations) {
        RatioGroup[] groups = new RatioGroup[MAX_P + 1];
        for (int p = 0; p <= MAX_P; p += 1) {
            RatioGroup g = groups[p];
            g.p = p;
            g.configurations = new ArrayList<>();
        }

        for (Configuration c: configurations) {
            int r = getRatio(c.ram, c.cpu);
            int p = logRatio(r);
            if (p > MAX_P) {
                throw new IllegalArgumentException("Illegal ratio logarithm: " + p);
            }

            RatioGroup g = groups[p];
            g.configurations.add(c);
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
