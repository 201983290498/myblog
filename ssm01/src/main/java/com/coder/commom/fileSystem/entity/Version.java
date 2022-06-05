package com.coder.commom.fileSystem.entity;

import com.coder.commom.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @Author coder
 * @Date 2022/5/30 23:23
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableField(tableName = "version_management")
public class Version implements Serializable{


    /**
     * 版本号, 唯一标识某个文件的某个状态
     */
    @TableField(column = "当前的版本id", tableColumn = "version_id")
    String versionId=UUID.randomUUID().toString();

    /**
     * 前一个版本的versionId,方便存储以逗号分隔开
     */
    @TableField(column = "前一个版本的id")
    String preVersion;

    /**
     * 下一个版本的versionId,以逗号分隔开
     */
    @TableField(column = "下一个版本的id")
    String nextVersion;

    /**
     * 之前的所有版本号, 非存储字段, 在service层自动处理获取
     */
    public List<String> preVersions;

    /**
     * 之后的所有版本号, 存储字段, 在service层自动处理获取,空值返回空列表
     */
    public List<String> nextVersions;

}
