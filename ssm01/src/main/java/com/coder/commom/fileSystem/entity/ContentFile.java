package com.coder.commom.fileSystem.entity;
import com.coder.commom.annotation.Enum.FileType;
import com.coder.commom.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录文件的功能，记录该文件下的目录目录项，
 * 1.那如何来构建一个文件和目录之间的关系呢？ ==> 需要一张目录表记录所有的目录.
 *
 * @Author coder
 * @Date 2022/6/1 0:10
 * @Description
 */
@Data
@TableField(tableName = "content_table")
public class ContentFile extends FileBase{

    {
        /**
         * 代码块，当文件类会被实例化时赋值
         */
        this.fileType = FileType.CONTENT;
    }

    /**
     * 子文件集合
     */
    List<File> subFiles = new ArrayList<>();

}
