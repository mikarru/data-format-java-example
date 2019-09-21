package com.example.mikarru.data.format.main;

import java.io.IOException;
import java.util.Arrays;

import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.UserCodec;
import com.example.mikarru.data.format.avro.AvroUserCodec;
import com.example.mikarru.data.format.json.JsonUserCodec;
import com.example.mikarru.data.format.protobuf.ProtobufUserCodec;

public class CompareSize {
    private static final User USER = User
            .builder()
            .id(1L)
            .name("Ken")
            .age(21)
            .links(Arrays.asList("http://ken.example.com/"))
            .build();
    public static void main(String[] args) throws IOException {
        AvroUserCodec avroCodec = new AvroUserCodec();
        JsonUserCodec jsonCodec = new JsonUserCodec();
        ProtobufUserCodec protobufCodec = new ProtobufUserCodec();

        showSize(avroCodec, USER);
        showSize(jsonCodec, USER);
        showSize(protobufCodec, USER);
    }

    public static void showSize(UserCodec codec, User user) throws IOException {
        byte[] bytes = codec.serialize(user);
        System.out.println(codec.getClass().getName() + ": " + bytes.length + " byte");
    }
}
