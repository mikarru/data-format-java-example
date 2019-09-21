package com.example.mikarru.data.format;

import java.util.Arrays;

public class TestUtil {
    private TestUtil() {}

    public static final User USER = User
            .builder()
            .id(1L)
            .name("Ken")
            .age(21)
            .links(Arrays.asList("http://ken.example.com/"))
            .build();
}
