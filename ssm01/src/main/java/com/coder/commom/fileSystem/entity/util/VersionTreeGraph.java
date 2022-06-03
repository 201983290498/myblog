package com.coder.commom.fileSystem.entity.util;


import com.coder.commom.fileSystem.entity.Version;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 版本树的基本解构，包括了每个版本的基本信息和
 * @Author coder
 * @Date 2022/6/2 9:16
 * @Description
 */
@Data
public class VersionTreeGraph {
    /**
     * 顶点集合
     */
    public HashMap<String, Version> dots = new HashMap<>();

    /**
     * 边的集合，关键字是版本号，值是对应的版本边
     */
    public HashMap<String, List<VersionEdge>> versionGraph = new HashMap<>();

    /**
     * 根节点的id
     */
    String rootId;

    /**
     * 获取某个节点前一个节点的详细信息
     * @param dotId 节点id
     * @return
     */
    public List<Version> findPreVersions(String dotId){
        List<Version> versions = new ArrayList<>();
        Version st = dots.get(dotId);
        for(String versionId: st.getPreVersions()){
            versions.add(dots.get(versionId));
        }
        return versions;
    }

    /**
     * 获取某个节点后继的详细信息
     * @param dotId 节点id
     * @return
     */
    public List<Version> findNextVersions(String dotId){
        List<Version> versions = new ArrayList<>();
        Version st = dots.get(dotId);
        for(String versionId: st.getNextVersions()){
            versions.add(dots.get(versionId));
        }
        return versions;
    }

}
