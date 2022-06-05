package com.coder.commom.fileSystem.dao;

import com.coder.commom.fileSystem.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * TODO 测试
 * @Author coder
 * @Date 2022/6/1 8:05
 * @Description
 */
@CacheNamespace(blocking = true)
public interface ContentFileDao {

    String TBLNAME = "tbl_content";

    /**
     * 寻找某个文件夹下所有激活的文件
     * @return
     */
    @Results(id="file", value = {
            @Result(property = "fileId", column = "file_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "fileType", column = "file_type"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "versionId", column = "version_id")
    })
    @Select("select a.* " +
            "from file_table a join "+ TBLNAME + " b on a.file_id = d.sub_directory " +
            "where b.directory = #{contentFileId} and a.is_active = true")
    List<File> listDir(String contentFileId);

    @Insert("insert into " + TBLNAME + "(directory, sub_directory) values(#{arg0}, #{arg1})")
    void insert(String dirFileId, String subDirFileId);

}
