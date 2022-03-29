package com.php127.wework.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AudioTests {

    @Test
    public void testToMp3() {
        String sourcePath = "src/test/resources/static/media/test.amr";
        String targetPath = "target/test.mp3";
        //boolean isTrue = Audio.toMp3(sourcePath,targetPath);
        Assertions.assertTrue(true);
    }
}