package com.example.mikarru.data.format.avro;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.example.mikarru.data.format.TestUtil;

public class AvroUserCodecTest {
    private AvroUserCodec codec = new AvroUserCodec();

    @Test
    public void serdeTest() throws IOException {
        Assert.assertEquals(TestUtil.USER,
                            codec.deserialize(codec.serialize(TestUtil.USER)));
    }
}
