package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.annotation.Enum.FileType;
import com.coder.commom.fileSystem.dao.ContentFileDao;
import com.coder.commom.fileSystem.entity.FileBase;
import com.coder.commom.fileSystem.service.ContentFileService;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @Author coder
 * @Date 2022/6/4 15:06
 * @Description
 */
@Data
@Service("ContentServiceImp")
public class ContentFileServiceImp implements ContentFileService {

    private final FileServiceImp fileService;

    private final ContentFileDao contentDao;

    public ContentFileServiceImp(FileServiceImp fileService, ContentFileDao contentDao) {
        this.fileService = fileService;
        this.contentDao = contentDao;
    }

    @Override
    public boolean insertOneContentFile(FileBase file, String faPath){
        //设置文件类型
        file.setFileType(FileType.CONTENT);
        return fileService.insertFile(file, faPath);
    }

    /**
     * 更新目录表tbl_content
     *
     * @param faPath   父亲文件的路径
     * @param sonPath  子文件的路径
     * @param username 文件的拥有者
     */
    @Override
    public void insertContent(String faPath, String sonPath, String username) {
        FileBase father = fileService.selectOneByFilepathAndUsername(faPath, username);
        FileBase son = fileService.selectOneByFilepathAndUsername(sonPath, username);
        contentDao.insert(father.getFileId(), son.getFileId());
    }


}
