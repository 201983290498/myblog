package com.coder.commonBase.service.impl;

import com.coder.commonBase.dao.BlackIpDao;
import com.coder.commonBase.entity.visit.BlackIp;
import com.coder.commonBase.service.BlackIpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * The type Black ip service imp.
 *
 * @Author coder
 * @Date 2021 /11/26 21:35
 * @Description
 */
@Service("blackIpServiceImpl")
@Data
public class BlackIpServiceImp implements BlackIpService {

    private final BlackIpDao blackIpMapper;

  public BlackIpServiceImp(@Qualifier("blackIpDao") BlackIpDao blackIpMapper) {
    this.blackIpMapper = blackIpMapper;
  }

  @Override
    public int deleteByPrimaryKey(Integer id) {
        return blackIpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlackIp record) {
        return blackIpMapper.insert(record);
    }

    @Override
    public int insertSelective(BlackIp record) {
        return blackIpMapper.insertSelective(record);
    }

    @Override
    public BlackIp selectByPrimaryKey(Integer id) {
        return blackIpMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlackIp record) {
        return blackIpMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlackIp record) {
        return blackIpMapper.updateByPrimaryKey(record);
    }

    @Override
    public BlackIp selectBlackIpByIp(String ip) {
        return blackIpMapper.selectBlackIpByIp(ip);
    }

    @Override
    public Long selectAllBlackIpCount() {
        return blackIpMapper.selectAllBlackIpCount();
    }

    @Override
    public List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map) {
        return blackIpMapper.selectLikeBlackIpListByPage(map);
    }

}
