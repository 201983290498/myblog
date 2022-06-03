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
public interface FileDao {

    String TBLNAME = "file_table";

    /**
     * 插入一个文件
     * @param file
     * @return
     */
    @Insert("insert into " + TBLNAME + "(file_id,filename, username, file_type, modified_time, filepath, version_id) " +
            "values(#{fileId},#{fileName},#{username},#{fileType}, #{modifiedTime}, #{filepath}, #{versionId})")
    int insert(File file);

    /**
     * 按照file_id找一个当前活跃的文件
     * @param fileId 版本号
     * @return
     */
    @Results(id="file", value = {
            @Result(property = "fileId", column = "file_id"),
            @Result(property = "modifiedTime", column = "modified_time"),
            @Result(property = "fileType", column = "file_type"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "versionId", column = "version_id")
    })
    @Select("select * from" + TBLNAME + "where file_id = #{fileId} and is_active = true")
    File selectOneByFileId(String fileId);

    @ResultMap(value="file")
    @Select("select * from" + TBLNAME + "where version_id = #{versionId}")
    File selectOneByVersionId(String versionId);

    /**
     * 更新
     * @param versionId 版本Id
     * @param active 激活的版本
     * @return
     */
    @Update("updae " + TBLNAME + "set is_active = #{active} where version = #{versionId}")
    boolean changeStatus(String versionId, boolean active);

    /**
     * todo 待测试从数据库获取某个字段的列表
     * 获取某个文件的所有的版本号
     * @param fileId 文件Id
     * @return 返回同一个文件的不同版本号
     */
    @Select("select version_id from " + TBLNAME + "where file_id = #{fileId}" )
    List<String> getVersionsOfFile(String fileId);
}
