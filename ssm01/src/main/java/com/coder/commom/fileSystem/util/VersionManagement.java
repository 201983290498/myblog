package com.coder.commom.fileSystem.util;

import com.coder.commom.fileSystem.entity.Version;

public interface VersionManagement {

    /**
     * 将数据库中压缩的前后继转换成列表节点
     * @param version 需要进一步处理的version
     */
    void getNeiberVersions(Version version);

    /**
     * 给新文件生成一个版本号
     * @param fileVersion 待填充的版本对象
     */
    void generateVersion(Version fileVersion);

    /**
     * 文件项存在两种: 1-首节点 ; 2-更新节点
     * 1. 首节点,它的前后缀都是空的
     * 2. 中间节点, 首先需要修改上一个节点的next, 在插入当前的节点
     * @param fileVersion 待插入的文件目录节点
     */
    void insertNewVersion(Version fileVersion);
}
