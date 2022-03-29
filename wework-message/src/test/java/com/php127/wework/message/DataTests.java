package com.php127.wework.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataTests {


    @Test
    public void test_init(){

        Data data = new Data();
        data.setSeq(1);
        System.out.println(data.getData());
    }
}
