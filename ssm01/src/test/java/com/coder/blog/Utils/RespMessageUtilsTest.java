package com.coder.blog.Utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RespMessageUtilsTest {

    @Test
    public void SUCCESS() {
        Map data = new HashMap<>();
        data.put("imageId",null);
        System.out.println(RespMessageUtils.SUCCESS(data));
    }

    @Test
    public void ERROR() {
    }
}