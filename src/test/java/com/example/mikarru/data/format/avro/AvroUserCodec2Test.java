package com.example.mikarru.data.format.avro;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.example.mikarru.data.format.TestUtil;

public class AvroUserCodec2Test {
    private AvroUserCodec2 codec = new AvroUserCodec2();

    @Test
    public void serdeTest() throws IOException {
        Assert.assertEquals(TestUtil.USER,
                            codec.deserialize(codec.serialize(TestUtil.USER)));
    }
}
