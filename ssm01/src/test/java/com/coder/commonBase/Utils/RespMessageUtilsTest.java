package com.coder.commonBase.Utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RespMessageUtilsTest {

    @Test
    public void SUCCESS() {
        Map data = new HashMap<>();
        data.put("imageId","null");
        System.out.println(RespMessageUtils.SUCCESS(data));
        System.out.println(RespMessageUtils.SUCCESS(1));
    }

    @Test
    public void ERROR() {
    }

    @Test
    public void testSUCCESS() {

    }
}
