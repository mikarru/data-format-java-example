package com.example.mikarru.data.format.json;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.example.mikarru.data.format.TestUtil;

public class JsonUserCodecTest {
    private JsonUserCodec codec = new JsonUserCodec();

    @Test
    public void serdeTest() throws IOException {
        Assert.assertEquals(TestUtil.USER,
                            codec.deserialize(codec.serialize(TestUtil.USER)));
    }
}
