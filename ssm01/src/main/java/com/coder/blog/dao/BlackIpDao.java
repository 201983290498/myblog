package com.coder.blog.dao;


import com.coder.blog.entity.visit.BlackIp;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

/**
 * The interface Black ip dao.
 *
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface BlackIpDao {

    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    @Sql
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(BlackIp record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(BlackIp record);

    /**
     * Select by primary key black ip.
     *
     * @param id the id
     * @return the black ip
     */
    BlackIp selectByPrimaryKey(Integer id);

    /**
     * Select black ip by ip black ip.
     *
     * @param ip the ip
     * @return the black ip
     */
    BlackIp selectBlackIpByIp(String ip);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(BlackIp record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(BlackIp record);

    /**
     * 模糊查询
     *
     * @param map the map
     * @return list
     */
    List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map);

    /**
     * Select all black ip count long.
     *
     * @return the long
     */
    Long selectAllBlackIpCount();
}
