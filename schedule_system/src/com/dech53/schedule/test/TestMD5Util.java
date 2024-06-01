package com.dech53.schedule.test;

import com.dech53.schedule.util.MD5Util;
import org.testng.annotations.Test;

public class TestMD5Util {
    @Test
    public void testencrypt(){
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
