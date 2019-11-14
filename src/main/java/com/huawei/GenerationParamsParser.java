package com.huawei;

import org.apache.commons.cli.*;

public class GenerationParamsParser {

    private final Options options;

    public GenerationParamsParser() {
        this.options = new Options();

        Option hostsOption = Option.builder("h")
                .required()
                .longOpt("hosts")
                .desc("Number of hosts in configuration")
                .hasArg()
                .build();
        options.addOption(hostsOption);

        Option vmsOption = Option.builder("v")
                .required()
                .longOpt("vms")
                .desc("Number of VMs in configuration")
                .hasArg()
                .build();
        options.addOption(vmsOption);

        Option outputOption = Option.builder("o")
                .required()
                .longOpt("output")
                .desc("Name of output file")
                .hasArg()
                .build();
        options.addOption(outputOption);
    }

    public GenerationParams parse(String[] args) {
        CommandLineParser clp = new DefaultParser();
        try {
            CommandLine line = clp.parse(options, args);
            GenerationParams params = new GenerationParams();
            setParams(line, params);
            return params;
        } catch (ParseException e) {
            new HelpFormatter().printHelp("generate", options);
            throw new RuntimeException("Failed to parse command line arguments", e);
        }
    }

    private void setParams(CommandLine line, GenerationParams params) {
        String hostsString = line.getOptionValue("hosts");
        int hostsInt = Integer.parseUnsignedInt(hostsString.trim());
        if (hostsInt <= 0) {
            throw new IllegalArgumentException("hosts must be > 0");
        }
        params.hosts = hostsInt;

        String vmsString = line.getOptionValue("vms");
        int vmsInt = Integer.parseUnsignedInt(vmsString);
        if (vmsInt <= 0) {
            throw new IllegalArgumentException("vms must be > 0");
        }
        params.vms = vmsInt;

        String outputString = line.getOptionValue("output");
        if ("".equals(outputString)) {
            throw new IllegalArgumentException("output must not be empty");
        }
        params.output = outputString;
    }
}
