package com.example.mikarru.data.format;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface UserCodec {
    interface UserWriter {
        void write(User user) throws IOException;
        void close() throws IOException;
    }

    interface UserReader {
        void read(User user) throws IOException;
        boolean hasNext() throws IOException;
        void close() throws IOException;
    }

    byte[] serialize(User user) throws IOException;
    User deserialize(byte[] bytes) throws IOException;
    UserWriter getWriter(OutputStream out) throws IOException;
    UserReader getReader(InputStream in) throws IOException;
}
