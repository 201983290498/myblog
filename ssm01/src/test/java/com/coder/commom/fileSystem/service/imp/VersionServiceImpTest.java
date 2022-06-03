package com.coder.commom.fileSystem.service.imp;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VersionServiceImpTest {

    @Test
    public void insert() {
        List<String> list = new ArrayList<>();
        list.add("saa");
        list.add("sbb");
        list.add("scc");
        System.out.println(Joiner.on(",").join(list));
    }
}