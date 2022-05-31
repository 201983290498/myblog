package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.fileSystem.dao.VersionDao;
import com.coder.commom.fileSystem.entity.Version;
import com.coder.commom.fileSystem.service.VersionService;
import com.google.common.base.Joiner;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author coder
 * @Date 2022/5/31 0:24
 * @Description
 */
@Service("versionServiceImpl")
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
     * 删除某个分支，以及之后的所有子节点，
     * 因为是一颗树,需要用一个先广度优先搜索将所有的子节点找到
     * @param version
     */
    @Override
    public boolean delete(Version version) {


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
        version.setPreVersions(List.of(version.getPreVersion().split(",")));
        version.setPreVersions(List.of(version.getNextVersion().split(",")));
        return version;
    }
}
