package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.fileSystem.dao.FileDao;
import com.coder.commom.fileSystem.entity.File;
import com.coder.commom.fileSystem.service.FileService;
import com.coder.commom.fileSystem.service.VersionService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基本的文档管理层
 * @Author coder
 * @Date 2022/6/1 13:30
 * @Description
 */
@Data
@Service("fileServiceImp")
public class FileServiceImp implements FileService {

    private final FileDao fileDao;
    private final VersionService versionService;

    public FileServiceImp(FileDao fileDao, VersionService versionService) {
        this.fileDao = fileDao;
        this.versionService = versionService;
    }


    /**
     * 切换当前激活的文件，实现文档替换,需要调用selectOne函数
     *
     * @param active    当前处于激活状态的文档
     * @param versionId 目标文档的Id
     * @return
     */
    @Override
    public File changeVersion(File active, String versionId) {
        active.setIsActive(!active.getIsActive());
        fileDao.changeStatus(active.getVersionId(), active.getIsActive());
        File activeFile = selectOneByVersionId(versionId);
        activeFile.setIsActive(!activeFile.getIsActive());
        fileDao.changeStatus(versionId, activeFile.getIsActive());
        return activeFile;
    }

    /**
     * 通过版本号筛选一个文件，更更新文件的版本信息
     * @param versionId 版本号
     * @return
     */
    @Override
    public File selectOneByVersionId(String versionId){
        File file = fileDao.selectOneByVersionId(versionId);
        //更新文件的版本信息
        versionService.setFileVersion(file);
        return file;
    }

    /**
     * 根据文件号获取到对应的文件
     * @param file_id 文件标识符
     * @return
     */
    @Override
    public File selectOneByFileId(String file_id){
        File file = fileDao.selectOneByFileId(file_id);
        versionService.setFileVersion(file);
        _setVersionsCollections(file);
        return file;
    }

    /**
     * 获取文件的所有版本号
     * @param file 待更新的文件
     */
    @Override
    public void _setVersionsCollections(File file) {
        //对得到的多个结果进行封装
        String fileId = file.getFileId();
        List<String> versionList = fileDao.getVersionsOfFile(fileId);
        //设置文件的所有版本
        file.setVersionList(versionList);
    }


}
