package com.coder.commom.fileSystem.service;

import com.coder.commom.fileSystem.entity.FileBase;

/**
 * @Author coder
 * @Date 2022/6/4 15:05
 * @Description
 */
public interface ContentFileService {
    /**
     * 插入一个文件, 已知的信息基本的是：用户名和文件路径, 可能存在的数据是二进制文件数据
     * @param file 待插入的文件
     * @return
     */
    boolean insertOneContentFile(FileBase file);
}
