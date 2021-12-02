package com.coder.blog.dao;


import com.coder.blog.entity.visit.BlackIp;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

/**
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface BlackIpDao {

    @Sql
    int deleteByPrimaryKey(Integer id);

    int insert(BlackIp record);

    int insertSelective(BlackIp record);

    BlackIp selectByPrimaryKey(Integer id);

    BlackIp selectBlackIpByIp(String ip);

    int updateByPrimaryKeySelective(BlackIp record);

    int updateByPrimaryKey(BlackIp record);

    /**
     * 模糊查询
     * @param map
     * @return
     */
    List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map);

    Long selectAllBlackIpCount();
}
