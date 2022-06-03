package com.coder.commom.fileSystem.util;

import com.coder.commom.fileSystem.entity.File;
import com.coder.commom.fileSystem.entity.Version;
import com.coder.commom.fileSystem.entity.util.VersionEdge;
import com.coder.commom.fileSystem.entity.util.VersionTreeGraph;
import com.coder.commom.fileSystem.service.VersionService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author coder
 * @Date 2022/6/2 14:42
 * @Description
 */
@Data
@Component(value = "versionTreeFactory")
public class VersionTreeFactory {

    /**
     * 自动注入versionService，对版本表格进一步的研究
     */
    private final VersionService versionService;

    public VersionTreeFactory(VersionService versionService) {
        this.versionService = versionService;
    }

    /**
     * 根据文件建立他的版本树
     * @param file 待建立版本控制的文件
     * @return 返回建立好的树
     */
    public VersionTreeGraph buildVersionTree(File file){
        return buildVersionTree(file.getVersionList());
    }

    /**
     * 根据已给的版本集合构建版本树
     * @param versionList 版本树的版本Id集合
     * @return 返回建立好的树
     */
    public VersionTreeGraph buildVersionTree(List<String> versionList){
        //构建一棵树
        VersionTreeGraph versionTree = new VersionTreeGraph();
        List<Version> versions = versionService.selectList(versionList);
        //先构建节点的所有信息
        for(Version version: versions){
            String temVersionId = version.getVersionId();
            //将所有的节点信息放入到树里面
            versionTree.dots.put(temVersionId, version);
            //如果没有前继就是根节点
            if(version.getPreVersions().size() == 0){
                versionTree.setRootId(temVersionId);
            }
            // 构建这个节点的单向树
            List<VersionEdge> edges = new ArrayList<>();
            for(String nextVersionId: version.getNextVersions()){
                edges.add(new VersionEdge(temVersionId, nextVersionId));
            }
        }
        return versionTree;
    }

    /**
     * 删除这个节点下面的所有节点
     * @param versionTree 版本树
     * @param cutPoint 待删除的树的节点
     * @return 删除成功-true, 删除失败-false
     */
    public boolean cutVersionTree(VersionTreeGraph versionTree, String cutPoint){
        //先找到所有的孩子节点
        List<String> children = new ArrayList<>();
        Queue<Version> queue = new ArrayDeque<>();
        Version root = versionTree.dots.get(cutPoint);
        queue.add(root);
        //广度优先遍历获取子树的所有节点
        while(!queue.isEmpty()){
            Version version = queue.poll();
            for(String nextVersion : version.getNextVersions()){
                children.add(nextVersion);
                queue.add(versionTree.dots.get(nextVersion));
            }
        }
        //对数据库进行修改
        versionService.deleteListByIds(children);
        //对根节点进行修改
        root.nextVersions.clear();
        versionService.updateOne(root);
        //树本身进行修改, 其中包括了点的信息 和 该点对应边的信息
        for(String deleteId: children){
            versionTree.dots.remove(deleteId);
            versionTree.versionGraph.remove(deleteId);
        }
        //删除该点出去的边
        versionTree.dots.put(root.getVersionId(),root);
        versionTree.versionGraph.get(root.getVersionId()).clear();
        return true;
    }

    /**
     * 将树从某个节点网下删除，对于每个节点需要做两部，删除后面的所有节点，删除自己，这里的boolean代表是否删除自己
     * @param versionTree 版本树
     * @param cutPoint 待删除的切入点
     * @param include 是否删除本身这个点
     * @return 返回值
     */
    public boolean cutVersionTree(VersionTreeGraph versionTree, String cutPoint, boolean include){
        cutVersionTree(versionTree, cutPoint);
        Version cutVersion = versionTree.dots.get(cutPoint);
        if(include){
            for(String faId: cutVersion.getPreVersions()){
                //todo 检测一下，当从map取出一个结构体进行修改的时候树本身是否发生修改
                Version faVersion = versionTree.dots.get(faId);
                faVersion.nextVersions.remove(cutPoint);
                versionService.updateOne(faVersion);
                //删除父亲节点的边
                List<VersionEdge> versionEdges = versionTree.versionGraph.get(faId);
                for(int i=0;i<versionEdges.size();i++){
                    if(versionEdges.get(i).getVersionFromId().equals(faId) && versionEdges.get(i).getVersionToId().equals(cutPoint) ){
                        versionEdges.remove(i);
                    }
                }
            }
            versionService.delete(cutVersion);
        }
        return true;
    }
}
