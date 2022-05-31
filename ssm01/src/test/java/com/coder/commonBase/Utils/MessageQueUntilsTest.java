package com.coder.commonBase.Utils;

import com.coder.commonBase.Utils.Queue.MessageQueUntils;
import com.coder.commonBase.exception.MessageException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-context.xml", "classpath:spring/applicationContext-mybatis.xml"})
public class MessageQueUntilsTest {

    @Autowired
    private MessageQueUntils messageQueUntils;

    private String validatorInfo;

    @Test
    public void generateMsg() {
        validatorInfo =messageQueUntils.generateMsg("1023668958@qq.com");
        checkMsg();
    }

    public void checkMsg() {
        try {
            messageQueUntils.checkMsg("1023668958@qq.com",validatorInfo);
            System.out.println("success");
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }
}