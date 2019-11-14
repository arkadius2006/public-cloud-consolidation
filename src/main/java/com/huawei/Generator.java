package com.huawei;

import java.util.HashMap;
import java.util.Map;

public class Generator {
    private final int hosts;
    private final int vms;

    public Generator(int hosts, int vms) {
        if (hosts <= 0) {
            throw new IllegalArgumentException("hosts <= 0");
        }

        if (vms <= 0) {
            throw new IllegalArgumentException("vms <= 0");
        }

        this.hosts = hosts;
        this.vms = vms;
    }

    // for now, do not respect capacity violations
    public CloudConfiguration generate() {
        Map<String,MachineConfiguration> hostConfigurations = new HashMap<>();
        for (int i = 0; i < this.hosts; i += 1) {
            String id = String.format("host-%04d", i);
            MachineConfiguration conf = new MachineConfiguration();
            conf.cpu = 1;
            conf.ram = 1;
            hostConfigurations.put(id, conf) ;
        }

        Map<String,MachineConfiguration> vmConfigurations = new HashMap<>();
        for (int j = 0; j < this.vms; j += 1) {
            String id = String.format("vm-%04d", j);
            MachineConfiguration conf = new MachineConfiguration();
            conf.cpu = 1;
            conf.ram = 1;

            vmConfigurations.put(id, conf);
        }

        Map<String, String> locations = new HashMap<>();
        String[] vmIDs = vmConfigurations.keySet().toArray(new String[0]);
        String[] hostIDs = hostConfigurations.keySet().toArray(new String[0]);

        int hostSeq = 0;
        for (int vmSeq = 0; vmSeq < this.vms; vmSeq += 1) {
            String vmID = vmIDs[vmSeq];
            String hostID = hostIDs[hostSeq];
            locations.put(vmID, hostID);

            hostSeq += 1;
            if (hostSeq >= this.hosts) {
                hostSeq = 0;
            }
        }

        CloudConfiguration cc = new CloudConfiguration();
        cc.hostConfigurations = hostConfigurations;
        cc.vmConfigurations = vmConfigurations;
        cc.locations = locations;
        return cc;
    }
}
