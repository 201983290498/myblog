package com.coder.commom.fileSystem.service;

import com.coder.commom.fileSystem.entity.File;
import com.coder.commom.fileSystem.entity.FileBase;

/**
 * @author coder
 */
public interface FileService {

    /**
     * 切换当前激活的文件，实现文档替换
     * @param active 当前处于激活状态的文档
     * @param versionId 目标文档的Id
     * @return
     */
    File changeVersion(File active, String versionId);

    /**
     * 通过版本号获取某个文件，所有的文件都有一个唯一的版本号
     * @param versionId 版本id
     * @return
     */
    File selectOneByVersionId(String versionId);

    /**
     * 通过文件id 获取当前行活跃的文件
     * @param fileId 文件Id
     * @return
     */
    File selectOneByFileId(String fileId);

    /**
     * 获取文件的所有版本号
     * @param file 待更新的文件
     */
    void _setVersionsCollections(File file);

    /**
     * 插入一个新的文件
     * @param file 文件目录信息
     * @param faPath 父亲文件的路径,只有新增的文件才有
     * @return
     */
    boolean insertFile(FileBase file, String faPath);

    /**
     * 根据路径和用户名查找文件
     * @param filepath 文件路径
     * @param username 文件拥有这
     * @return
     */
    FileBase selectOneByFilepathAndUsername(String filepath, String username);
}
