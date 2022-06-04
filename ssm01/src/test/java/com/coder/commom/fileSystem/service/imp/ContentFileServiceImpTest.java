package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.fileSystem.entity.FileBase;
import com.coder.commom.fileSystem.service.ContentFileService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ContentFileServiceImpTest {

    @Autowired
    private ContentFileService contentFileService;

    @Test
    public void insertOneContentFile() {
        FileBase fileBase = new FileBase();
        fileBase.setUsername("Coder1");
        contentFileService.insertOneContentFile(fileBase);
    }
}