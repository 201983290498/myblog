package com.coder.commom.fileSystem.service;

import com.coder.commom.fileSystem.entity.FileBase;

import java.util.List;

/**
 * @Author coder
 * @Date 2022/6/4 15:05
 * @Description
 */
public interface ContentFileService {
    /**
     * 插入一个文件, 已知的信息基本的是：用户名和文件路径, 可能存在的数据是二进制文件数据
     * 更新文件表和版本表
     * @param file 待插入的文件
     * @param faPath 跟新目录需要, 如果是更新或者没有父亲设置为null
     * @return
     */
    boolean insertOneContentFile(FileBase file, String faPath);

    /**
     * 更新目录表tbl_content
     * @param faPath 父亲文件的路径
     * @param sonPath 子文件的路径
     * @param username 文件的拥有者
     */
    void insertContent(String faPath, String sonPath, String username);

    /**
     * 获取某个文件下的目录项
     * @param contentFileId 目录的文件ID
     * @return
     */
    List<FileBase> listDir(String contentFileId);
}
