package com.coder.commom.fileSystem.service;

import com.coder.commom.fileSystem.entity.Version;

import java.util.List;

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
     * TODO 删除某个分支，需要进行身份认证，可以通过AOP检查拦截检查
     * 删除某个分支，以及之后的所有子节点，
     * @param version 需要删除的版本
     */
    boolean delete(Version version);

    /**
     * 获取一个分支，需要初步的分析出前一个节点和后一个节点，方便构建树
     * @param versionId 需要选取的版本Id
     */
    Version selectOne(String versionId);

    /**
     * 想上层文件提供一个更新Version数据的方式
     * @param version
     */
    void setFileVersion(Version version);

    /**
     * 根据版本id获取到所有的版本信息
     * @param versionIds 版本id集合
     * @return 返回所有的版本信息
     */
    List<Version> selectList(List<String> versionIds);

    /**
     * 更新某个节点的信息
     * @param version 待更新的节点
     */
    void UpdateOne(Version version);

    /**
     * 批量删除
     * @param children 批量删除的节点集合
     */
    void deleteListByIds(List<String> children);
}
