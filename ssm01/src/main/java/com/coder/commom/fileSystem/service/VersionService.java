package com.coder.commom.fileSystem.service;

import com.coder.commom.fileSystem.entity.Version;

/**
 * @Author coder
 * @Date 2022/5/31 0:23
 * @Description
 */
public interface VersionService {

    /**
     * 存入相关版本，需要对前后节点进行存储处理
     * @param version 需要插入的版本
     */
    boolean insert(Version version);

    /**
     * //TODO 删除某个分支，需要进行身份认证，可以通过AOP检查拦截检查
     * 删除某个分支，以及之后的所有子节点，
     * @param version 需要删除的版本
     */
    boolean delete(Version version);

    /**
     * 获取一个分支，需要初步的分析出前一个节点和后一个节点，方便构建树
     * @param versionId 需要选取的版本Id
     */
    Version selectOne(String versionId);
}
