package com.example.mikarru.data.format.protobuf;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.example.mikarru.data.format.TestUtil;

public class ProtobufUserCodecTest {
    private ProtobufUserCodec codec = new ProtobufUserCodec();

    @Test
    public void serdeTest() throws IOException {
        Assert.assertEquals(TestUtil.USER,
                            codec.deserialize(codec.serialize(TestUtil.USER)));
    }
}