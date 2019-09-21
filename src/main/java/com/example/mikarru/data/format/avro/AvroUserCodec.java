package com.example.mikarru.data.format.avro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.UserCodec;

public class AvroUserCodec implements UserCodec {
    static com.example.mikarru.data.format.avro.generated.User convertToAvroUser(User user) {
        return com.example.mikarru.data.format.avro.generated.User
                .newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setAge(user.getAge())
                .setLinks(user.getLinks())
                .build();
    }

    static User convertFromAvroUser(com.example.mikarru.data.format.avro.generated.User avroUser) {
        return User
                .builder()
                .id(avroUser.getId())
                .name(avroUser.getName())
                .age(avroUser.getAge())
                .links(avroUser.getLinks())
                .build();
    }

    @Override
    public byte[] serialize(User user) throws IOException {
        return convertToAvroUser(user).toByteBuffer().array();
    }

    @Override
    public User deserialize(byte[] bytes) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        com.example.mikarru.data.format.avro.generated.User avroUser =
                com.example.mikarru.data.format.avro.generated.User.fromByteBuffer(byteBuffer);
        return convertFromAvroUser(avroUser);
    }

    public static class AvroUserWriter implements UserWriter {
        private static final SpecificDatumWriter<com.example.mikarru.data.format.avro.generated.User> USER_DATUM_WRITER =
                new SpecificDatumWriter<>(com.example.mikarru.data.format.avro.generated.User.class);

        private final DataFileWriter<com.example.mikarru.data.format.avro.generated.User> writer;

        public AvroUserWriter(OutputStream out) throws IOException {
            this.writer = new DataFileWriter<>(USER_DATUM_WRITER);
            writer.create(com.example.mikarru.data.format.avro.generated.User.getClassSchema(), out);
        }

        @Override
        public void write(User user) throws IOException {
            writer.append(convertToAvroUser(user));
        }

        @Override
        public void close() throws IOException {
            writer.close();
        }
    }

    public static class AvroUserReader implements UserReader {
        private static final SpecificDatumReader<com.example.mikarru.data.format.avro.generated.User> USER_DATUM_READER =
                new SpecificDatumReader<>(com.example.mikarru.data.format.avro.generated.User.class);

        private final DataFileReader<com.example.mikarru.data.format.avro.generated.User> reader;
        private com.example.mikarru.data.format.avro.generated.User reusableAvroUser;

        public AvroUserReader(InputStream in) {
            throw new UnsupportedOperationException();
        }

        public AvroUserReader(byte[] bytes) throws IOException {
            SeekableInput in = new SeekableByteArrayInput(bytes);
            this.reader = new DataFileReader<>(in, USER_DATUM_READER);
        }

        public AvroUserReader(SeekableInput in) throws IOException {
            this.reader = new DataFileReader<>(in, USER_DATUM_READER);
        }

        @Override
        public void read(User user) throws IOException {
            reusableAvroUser = reader.next(reusableAvroUser);
            user.setId(reusableAvroUser.getId());
            user.setName(reusableAvroUser.getName());
            user.setAge(reusableAvroUser.getAge());
            user.setLinks(reusableAvroUser.getLinks());
        }

        @Override
        public boolean hasNext() throws IOException {
            return reader.hasNext();
        }

        @Override
        public void close() throws IOException {
            reader.close();
        }
    }

    @Override
    public AvroUserWriter getWriter(OutputStream out) throws IOException {
        return new AvroUserWriter(out);
    }

    @Override
    public UserReader getReader(InputStream in) throws IOException {
        // TODO
        throw new UnsupportedOperationException();
    }

    public AvroUserReader getReader(SeekableInput in) throws IOException {
        return new AvroUserReader(in);
    }

    public AvroUserReader getReader(byte[] bytes) throws IOException {
        return new AvroUserReader(bytes);
    }
}
