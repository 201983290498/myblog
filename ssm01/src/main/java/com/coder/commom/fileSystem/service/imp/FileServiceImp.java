package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.annotation.Enum.FileType;
import com.coder.commom.fileSystem.dao.FileDao;
import com.coder.commom.fileSystem.entity.File;
import com.coder.commom.fileSystem.entity.FileBase;
import com.coder.commom.fileSystem.service.FileService;
import com.coder.commom.fileSystem.service.VersionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 基本的文档管理层
 * @Author coder
 * @Date 2022/6/1 13:30
 * @Description
 */
@Data
@Slf4j
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
     *
     * @param versionId 版本号
     * @return
     */
    @Override
    public File selectOneByVersionId(String versionId) {
        File file = fileDao.selectOneByVersionId(versionId);
        //更新文件的版本信息
        versionService.setFileVersion(file);
        return file;
    }

    /**
     * 根据文件号获取到对应的文件
     *
     * @param file_id 文件标识符
     * @return
     */
    @Override
    public File selectOneByFileId(String file_id) {
        File file = fileDao.selectOneByFileId(file_id);
        versionService.setFileVersion(file);
        _setVersionsCollections(file);
        return file;
    }

    /**
     * 获取文件的所有版本号
     *
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


    /**
     * 插入一个新的文件,一般比较唯一的就是文件路径和文件拥有者
     * - 目录具有唯一性
     * - 文件的话查看is_active 是添加文件目录还是添加新的激活文件
     *  {@link todo 文件更新的检测}
     *
     * @param file 文件目录信息
     * @return 返回是否插入成功
     */
    @Override
    public boolean insertFile(FileBase file) {
        //默认文本是file类型的，初始化文件的基本信息
        if (file.getFileType() != FileType.CONTENT) {
            file.setFileType(FileType.FILE);
        }
        versionService.generateVersion(file);
        file.generateFileInfo();
        //查看文件是否已经存在
        int exist = fileDao.findByFilepathAndVersion(file.getFilepath(), file.getVersionId());
        if (exist == 0) {
            //文件不存在直接创建
            file.setIsActive(true);
            fileDao.insert(file);
            log.info(file.toString());
        } else if (file.getIsActive() && exist > 0) {
            if (file.getFileType() == FileType.CONTENT) {
                return false;
            } else {
                //找到待更新的文档
                File file1 = fileDao.selectOneByFilepathAndUsername(file.getFilepath(), file.getUsername());
                //转变原本文档的状态
                fileDao.changeStatus(file1.getVersionId(),false);
                //设置新文档的id
                file.setFileId(file1.getFileId());
                fileDao.insert(file);
                log.info(file.toString());
            }
        } else {//文件已经存在
            return false;
        }
        return true;
    }
}
