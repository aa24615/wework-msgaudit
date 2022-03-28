package com.php127.wework.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Base64CodedTests {

    @Test
    public void testDecode() {
        Assertions.assertEquals("test", Base64Coded.decode("dGVzdA=="));
    }

    @Test
    public void testEncode() {
        Assertions.assertEquals("dGVzdA==", Base64Coded.encode("test"));
    }
}
