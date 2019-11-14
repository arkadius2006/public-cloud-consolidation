package com.huawei;

public class GenerationParamsParser {

    public GenerationParams parse(String[] args) {
        if (args.length  == 0) {
            throw new IllegalArgumentException("Not enough args");
        }

        if (args.length > 1) {
            throw new IllegalArgumentException("Too many args");
        }

        return parse(args[0]);
    }

    private GenerationParams parse(String line) {
        throw new RuntimeException("Not implemented");
    }
}
