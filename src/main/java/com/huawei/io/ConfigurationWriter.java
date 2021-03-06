package com.huawei.io;

import com.huawei.Cloud;

import java.io.IOException;
import java.io.Writer;

public interface ConfigurationWriter {

    void write(Cloud config, Writer w) throws IOException;
}
