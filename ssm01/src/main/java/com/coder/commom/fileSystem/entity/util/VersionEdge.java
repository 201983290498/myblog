package com.coder.commom.fileSystem.entity.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 版本树的边结构
 * @Author coder
 * @Date 2022/6/2 9:11
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionEdge {// 定义一个version的有向边

    /**
     * 版本的起点
     */
    String versionFromId;

    /**
     * 版本的终点
     */
    String versionToId;

    /**
     * 第几层节点
     */
    Integer layerId;

    /**
     * @param version1 版本的起点
     * @param version2 版本的终点
     */
    public VersionEdge(String version1, String version2){
        this.versionFromId = version1;
        this.versionToId = version2;
    }
}
