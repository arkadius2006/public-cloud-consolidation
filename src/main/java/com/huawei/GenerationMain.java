package com.huawei;

import com.huawei.algorithm.Cloud;
import com.huawei.algorithm.Generator;
import com.huawei.io.ConfigurationWriter;
import com.huawei.io.JsonConfigurationWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class GenerationMain {

    public static void main(String[] args) throws Exception {
        //  parse params
        GenerationParams params = new GenerationParamsParser().parse(args);

        // generation configuration
        Cloud config = new Generator(params.hosts, params.vms).generate();

        // dump tp file
        ConfigurationWriter writer = new JsonConfigurationWriter();
        writer.write(config, new BufferedWriter(new FileWriter(params.output)));

    }
}
