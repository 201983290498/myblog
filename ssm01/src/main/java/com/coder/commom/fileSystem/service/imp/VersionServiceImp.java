package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.fileSystem.dao.VersionDao;
import com.coder.commom.fileSystem.entity.Version;
import com.coder.commom.fileSystem.service.VersionService;
import com.google.common.base.Joiner;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * @Author coder
 * @Date 2022/5/31 0:24
 * @Description
 */
@Service("versionServiceImp")
@Data
public class VersionServiceImp implements VersionService {

    private final VersionDao versionDao;

    public VersionServiceImp(VersionDao versionDao) {
        this.versionDao = versionDao;
    }


    /**
     * 存入相关版本，需要对前后节点进行存储处理
     *
     * @param version 版本分支
     */
    @Override
    public boolean insert(Version version) {
        //图转成字符窜存储
        version.setPreVersion(Joiner.on(",").join(version.getPreVersions()));
        version.setNextVersion(Joiner.on(",").join(version.getNextVersions()));
        versionDao.insert(version);
        return true;
    }

    /**
     * TODO 删除某个分支，需要进行身份认证，可以通过AOP检查拦截检查
     * TODO 需要检查它的前缀是否都删除，由文件类保证
     * 删除某个分支，以及之后的所有子节点，
     * 树的构建交给文件，文件中有个集合包含了所有的版本id，由他来删除所有的版本
     * @param version
     */
    @Override
    public boolean delete(Version version) {
        versionDao.delete(version);
        return true;
    }

    /**
     * 获取一个分支，需要初步的分析出前一个节点和后一个节点，方便构建树
     *
     * @param versionId
     */
    @Override
    public Version selectOne(String versionId) {
        Version version = versionDao.selectOneById(versionId);
        getNeiberVersions(version);
        return version;
    }

    /**
     * 向上层文件提供一个版本相关的详细信息的方式
     * @param version 更新对应的文件的版本信息
     */
    @Override
    public void setFileVersion(Version version){
        Version newVersion = selectOne(version.getVersionId());
        version.setNextVersions(newVersion.getNextVersions());
        version.setNextVersion(newVersion.getNextVersion());
        version.setPreVersions(newVersion.getPreVersions());
        version.setPreVersion(newVersion.getPreVersion());
    }

    /**
     * 获取到所有的版本号
     * @param versionIds 版本id集合
     * @return
     */
    @Override
    public List<Version> selectList(List<String> versionIds) {
        List<Version> versions = versionDao.selectListByIds(versionIds);
        for(Version version: versions){
          getNeiberVersions(version);
        }
        return versions;
    }

    /**
     * 更新某个节点的信息
     *
     * @param version 待更新的节点
     */
    @Override
    public void UpdateOne(Version version) {
        //重置前后节点的信息
        version.setPreVersion(Joiner.on(",").join(version.getPreVersions()));
        version.setNextVersion(Joiner.on(",").join(version.getNextVersions()));
        versionDao.updateOne(version);
    }

    /**
     * 批量删除
     *
     * @param children 批量删除的节点集合
     */
    @Override
    public void deleteListByIds(List<String> versionIds) {
        versionDao.deleteListByIds(versionIds);
    }

    /**
     * 获取到附近的点
     * @param version 需要进一步处理的version
     */
    private static void getNeiberVersions(Version version) {
        if(version.getPreVersion()==null) {
            version.setPreVersion("");
        }
        if(version.getNextVersion() == null) {
            version.setNextVersion("");
        }
        version.setPreVersions(List.of(version.getPreVersion().split(",")));
        version.setNextVersions(List.of(version.getNextVersion().split(",")));
    }

}
