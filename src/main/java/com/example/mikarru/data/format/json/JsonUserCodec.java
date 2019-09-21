package com.example.mikarru.data.format.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.UserCodec;

public class JsonUserCodec implements UserCodec {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public byte[] serialize(User user) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(user);
    }

    public String serializeAsString(User user) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(user);
    }

    @Override
    public User deserialize(byte[] bytes) throws IOException {
        return OBJECT_MAPPER.readValue(bytes, User.class);
    }

    public User deserializeFromString(String string) throws IOException {
        return OBJECT_MAPPER.readValue(string, User.class);
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
