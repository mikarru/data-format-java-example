package com.example.mikarru.data.format.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.UserCodec;
import com.example.mikarru.data.format.protobuf.generated.UserOuterClass;

public class ProtobufUserCodec implements UserCodec {
    @Override
    public byte[] serialize(User user) throws IOException {
        UserOuterClass.User protobufUser = UserOuterClass.User
                .newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setAge(user.getAge())
                .addAllLinks(user.getLinks())
                .build();
        return protobufUser.toByteArray();
    }

    @Override
    public User deserialize(byte[] bytes) throws IOException {
        UserOuterClass.User protobufUser = UserOuterClass.User.parseFrom(bytes);
        return User
                .builder()
                .id(protobufUser.getId())
                .name(protobufUser.getName())
                .age(protobufUser.getAge())
                .links(protobufUser.getLinksList())
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
