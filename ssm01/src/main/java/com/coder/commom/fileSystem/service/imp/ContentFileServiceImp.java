package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.annotation.Enum.FileType;
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

    public ContentFileServiceImp(FileServiceImp fileService) {
        this.fileService = fileService;
    }

    @Override
    public boolean insertOneContentFile(FileBase file){
        //设置文件类型
        file.setFileType(FileType.CONTENT);
        fileService.insertFile(file);
        return false;
    }
}
