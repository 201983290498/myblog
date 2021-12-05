package com.coder.blog.service;

import com.coder.blog.entity.visit.BlackIp;

import java.util.List;
import java.util.Map;

/**
 * The interface Black ip service.
 */
public interface BlackIpService {
    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
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
     * Select all black ip count long.
     *
     * @return the long
     */
    Long selectAllBlackIpCount();

    /**
     * Select like black ip list by page list.
     *
     * @param map the map
     * @return the list
     */
    List<BlackIp> selectLikeBlackIpListByPage(Map<String, Object> map);
}
