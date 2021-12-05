package com.coder.blog.service.impl;

import com.coder.blog.dao.BlackIpDao;
import com.coder.blog.entity.visit.BlackIp;
import com.coder.blog.service.BlackIpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    @Qualifier("blackIpDao")
    private BlackIpDao blackIpMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return blackIpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.insert(record);
    }

    @Override
    public int insertSelective(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.insertSelective(record);
    }

    @Override
    public BlackIp selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return blackIpMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlackIp record) {
        // TODO Auto-generated method stub
        return blackIpMapper.updateByPrimaryKey(record);
    }

    @Override
    public BlackIp selectBlackIpByIp(String ip) {
        // TODO Auto-generated method stub
        return blackIpMapper.selectBlackIpByIp(ip);
    }

    @Override
    public Long selectAllBlackIpCount() {
        // TODO Auto-generated method stub
        return blackIpMapper.selectAllBlackIpCount();
    }

    @Override
    public List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return blackIpMapper.selectLikeBlackIpListByPage(map);
    }

}
