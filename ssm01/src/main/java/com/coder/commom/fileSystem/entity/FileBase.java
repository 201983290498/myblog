package com.coder.commom.fileSystem.entity;

import com.coder.commom.annotation.Enum.FileType;
import com.coder.commom.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 所有文件的基本结构，
 * 包括了版本的信息 ==> 建议版本的信息版本处理，对不同的类进行解耦,这样做的目的是为了让其他的类无缝的对接到上面去，因此需要设计一个工具bean来处理这样的问题
 * 文件的目录信息 ==>
 * @Author coder
 * @Date 2022/5/31 23:44
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileBase extends Version{

    /**
     * 文件标识符
     */
    @TableField(column = "文件标识", isKey = true, tableColumn = "file_id")
    String fileId;

    /**
     * 文件的拥有用户
     */
    @TableField(column = "文件拥有者")
    String username;

    /**
     * 文件的修改时间
     */
    @TableField(column = "修改时间")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    public Date updateTime;

    /**
     * 文件类型
     */
    @TableField(column = "文件类型")
    FileType fileType;

    /**
     * 文件名
     */
    @TableField(column = "文件名")
    String filename;

    /**
     * 文件路径路径唯一
     */
    @TableField(column = "文件路径")
    String filepath;

    /**
     * 是否是当前分支活跃的函数
     */
    Boolean isActive=false;

    public FileBase(String username, String filepath) {
        this.username=username;
        this.filepath=filepath;
    }

    public void generateFileInfo() {
        List<String> splitStrs= List.of(filepath.split("/"));
        filename = splitStrs.get(splitStrs.size()-1);
        updateTime = new Date();
        fileId = UUID.randomUUID().toString();
    }
}
