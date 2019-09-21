package com.example.mikarru.data.format.avro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.UserCodec;

public class AvroUserCodec2 implements UserCodec {
    private static final SpecificDatumWriter<com.example.mikarru.data.format.avro.generated.User>
            USER_DATUM_WRITER = new SpecificDatumWriter<>(com.example.mikarru.data.format.avro.generated.User.class);
    private static final SpecificDatumReader<com.example.mikarru.data.format.avro.generated.User>
            USER_DATUM_READER = new SpecificDatumReader<>(com.example.mikarru.data.format.avro.generated.User.class);

    @Override
    public byte[] serialize(User user) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().directBinaryEncoder(bo, null);
        USER_DATUM_WRITER.write(AvroUserCodec.convertToAvroUser(user), encoder);
        return bo.toByteArray();
    }

    @Override
    public User deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        Decoder decoder = DecoderFactory.get().directBinaryDecoder(bi, null);
        com.example.mikarru.data.format.avro.generated.User avroUser = USER_DATUM_READER.read(null, decoder);
        return AvroUserCodec.convertFromAvroUser(avroUser);
    }

    @Override
    public UserWriter getWriter(OutputStream out) {
        return null;
    }

    @Override
    public UserReader getReader(InputStream in) {
        return null;
    }
}
