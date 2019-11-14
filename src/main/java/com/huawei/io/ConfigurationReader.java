package com.huawei.io;

import com.huawei.CloudConfiguration;

import java.io.IOException;
import java.io.Reader;

public interface ConfigurationReader {

    CloudConfiguration read(Reader r) throws IOException;
}
