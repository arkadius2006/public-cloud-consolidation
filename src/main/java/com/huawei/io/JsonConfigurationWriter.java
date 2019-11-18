package com.huawei.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.huawei.Cloud;

import java.io.IOException;
import java.io.Writer;

public class JsonConfigurationWriter implements ConfigurationWriter {

    @Override
    public void write(Cloud config, Writer w) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter jsonWriter = mapper.writerWithDefaultPrettyPrinter();
        jsonWriter.writeValue(w, config);
    }
}
