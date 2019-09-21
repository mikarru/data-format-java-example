package com.example.mikarru.data.format.avro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.UserCodec;

public class AvroUserCodec implements UserCodec {
    static final DatumWriter<com.example.mikarru.data.format.avro.generated.User> userDatumWriter =
            new SpecificDatumWriter<>(com.example.mikarru.data.format.avro.generated.User.class);

    @Override
    public byte[] serialize(User user) throws IOException {
        com.example.mikarru.data.format.avro.generated.User avroUser =
                com.example.mikarru.data.format.avro.generated.User.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setAge(user.getAge())
                .setLinks(user.getLinks())
                .build();
        return avroUser.toByteBuffer().array();
    }

    @Override
    public User deserialize(byte[] bytes) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        com.example.mikarru.data.format.avro.generated.User avroUser =
                com.example.mikarru.data.format.avro.generated.User.fromByteBuffer(byteBuffer);
        return User
                .builder()
                .id(avroUser.getId())
                .name(avroUser.getName())
                .age(avroUser.getAge())
                .links(avroUser.getLinks())
                .build();
    }

    @Override
    public UserWriter getWriter(OutputStream out) throws IOException {
        return null;
    }

    @Override
    public UserReader getReader(InputStream in) throws IOException {
        return null;
    }
}
