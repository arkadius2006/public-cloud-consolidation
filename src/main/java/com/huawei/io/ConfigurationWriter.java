package com.huawei.io;

import com.huawei.CloudConfiguration;

import java.io.IOException;
import java.io.Writer;

public interface ConfigurationWriter {

    void write(CloudConfiguration config, Writer w) throws IOException;
}
