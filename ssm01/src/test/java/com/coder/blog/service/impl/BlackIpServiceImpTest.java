package com.coder.blog.service.impl;

import com.coder.blog.entity.visit.BlackIp;
import com.coder.blog.service.BlackIpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class BlackIpServiceImpTest {

    @Autowired
    private BlackIpService service;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test

    public void selectBlackIpByIp() {
        BlackIp blackIp = service.selectBlackIpByIp("127.0.0.1");
        System.out.println(blackIp);
    }

    @Test
    public void selectAllBlackIpCount() {
    }

    @Test
    public void selectLikeBlackIpListByPage() {
    }
}