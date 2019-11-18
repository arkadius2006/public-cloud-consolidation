package com.huawei.io;

import com.huawei.Cloud;

import java.io.IOException;
import java.io.Reader;

public interface ConfigurationReader {

    Cloud read(Reader r) throws IOException;
}
