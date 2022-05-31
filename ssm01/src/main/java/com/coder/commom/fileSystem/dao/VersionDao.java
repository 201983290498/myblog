package com.coder.commom.fileSystem.dao;

import com.coder.commom.fileSystem.entity.Version;
import org.apache.ibatis.annotations.*;

/**
 * {@link CacheNamespace} 开启二级缓存
 * @Author coder
 * @Date 2022/5/31 0:00
 * @Description
 */
@CacheNamespace(blocking = true)
public interface VersionDao {

    String tblName = "version_management";

    /**
     * 插入版本记录
     * @param versionRecord 当前的版本记录
     */
    @Insert("insert into version_management(version_id, pre_version_id, next_version_id) " +
            "values(#{versionId}, #{preVersionId}, #{nextVersionId})")
    void insert(Version versionRecord);

    /**
     * 查找上一个分支
     * @param version_Id 当有多个上一个分支的时候就需要service先进行处理
     * @return
     */
    @Results(id="version_id", value = {
            @Result(property = "versionId", column = "version_id"),
            @Result(property = "preVersionId", column = "pre_version_id"),
            @Result(property = "nextVersionId", column = "next_version_id")
    })
    @Select("select version_id, pre_version_id, next_version_id from version_management where version_id=#{arg0}")
    Version selectOneById(String version_Id);

    /**
     * 删除某一个分支
     * @param version
     */
    @Delete("delete from version_management where version_id=#{versionId}")
    void delete(Version version);
}
