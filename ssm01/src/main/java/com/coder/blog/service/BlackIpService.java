package com.coder.blog.service;

import com.coder.blog.entity.visit.BlackIp;

import java.util.List;
import java.util.Map;

public interface BlackIpService {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackIp record);

    int insertSelective(BlackIp record);

    BlackIp selectByPrimaryKey(Integer id);

    BlackIp selectBlackIpByIp(String ip);

    int updateByPrimaryKeySelective(BlackIp record);

    int updateByPrimaryKey(BlackIp record);

    Long selectAllBlackIpCount();

    List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map);
}
