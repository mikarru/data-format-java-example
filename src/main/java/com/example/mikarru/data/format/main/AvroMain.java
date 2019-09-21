package com.example.mikarru.data.format.main;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import com.example.mikarru.data.format.User;
import com.example.mikarru.data.format.avro.AvroUserCodec;
import com.example.mikarru.data.format.avro.AvroUserCodec.AvroUserReader;
import com.example.mikarru.data.format.avro.AvroUserCodec.AvroUserWriter;

public class AvroMain {
    public static void main(String[] args) throws Exception {
        User user = User
                .builder()
                .id(1L)
                .name("Tom")
                .age(22)
                .links(Arrays.asList("hoge"))
                .build();

        AvroUserCodec codec = new AvroUserCodec();
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        AvroUserWriter writer = codec.getWriter(bo);
        writer.write(user);
        user.setName("Ken");
        writer.write(user);
        user.setName("Bob");
        writer.write(user);
        writer.write(user);
        writer.close();
        byte[] bytes = bo.toByteArray();
        System.out.println("Size: " + bytes.length + " bytes");

        AvroUserReader reader = codec.getReader(bytes);
        while (reader.hasNext()) {
            reader.read(user);
            System.out.println(user);
        }
        reader.close();
    }
}
