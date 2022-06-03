package com.coder.commom.fileSystem.dao;

import com.coder.commom.fileSystem.entity.Version;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Results(id="version", value = {
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
    @Delete("delete from "+ tblName +" where version_id=#{versionId}")
    void delete(Version version);

    /**
     * 根据id集合获取到所有的版本,通过xml注入
     * @param versionIds 版本id的集合
     */
    List<Version> selectListByIds(List<String> versionIds);

    /**
     * 更新某个节点的信息
     * @param version 待跟新的版本
     */
    @Update("update " + tblName +
            "set pre_version_id = #{preVersionId} and next_version_id = #{nextVersionId} " +
            "where version_id = #{versionId}")
    boolean updateOne(Version version);

    /**
     * 批量删除某些版本节点
     * @param versionIds 待删除的版本节点
     */
    void deleteListByIds(List<String> versionIds);
}
