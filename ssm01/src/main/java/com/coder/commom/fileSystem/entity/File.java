package com.coder.commom.fileSystem.entity;

import com.coder.commom.annotation.Enum.FileType;
import com.coder.commom.fileSystem.entity.util.VersionTreeGraph;
import lombok.Data;

import java.util.List;

/**
 * @Author coder
 * @Date 2022/6/1 7:45
 * @Description
 */
@Data
public class File extends FileBase{

    {
        this.fileType = FileType.FILE;
    }


    /**
     * 版本的所有集合，
     */
    List<String> versionList = null;

    /**
     * 版本树，只有在必要的时候通过VersionTreeGraphUtil 获取
     */
    VersionTreeGraph versionTree = null;

}
