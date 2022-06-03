package com.coder.commom.fileSystem.dao;

import com.coder.commom.fileSystem.entity.File;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContentFileDao {

    /**
     * 寻找某个文件夹下所有激活的文件
     * @return
     */
    @ResultMap(value = "file")
    @Select("select a.* " +
            "from file_table a join content_table b on a.file_id = d.sub_directory " +
            "where b.directory = #{contentFileId} and a.is_active = true")
    List<File> listDir(Integer contentFileId);

}
