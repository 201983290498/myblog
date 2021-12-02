package com.coder.blog.service.impl;

import com.coder.blog.dao.VisitDao;
import com.coder.blog.entity.visit.Visit;
import com.coder.blog.service.VisitService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/11/26 23:12
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class VisitServiceImp implements VisitService {

    @Autowired
    private VisitDao visitDao;

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return visitDao.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int insert(Visit record) {
        // TODO Auto-generated method stub
        return visitDao.insert(record);
    }

    @Override
    public Visit selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return visitDao.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(Visit record) {
        // TODO Auto-generated method stub
        return visitDao.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(Visit record) {
        // TODO Auto-generated method stub
        return visitDao.updateByPrimaryKey(record);
    }

    @Override
    public Visit selectVisitByIp(String ip) {
        // TODO Auto-generated method stub
        return visitDao.selectVisitByIp(ip);
    }

    @Override
    public Long findVisitTimes(Visit visit) {
        // TODO Auto-generated method stub
        return visitDao.findVisitTimes(visit);
    }

    @Override
    public List<?> selectVisitListByDate(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return visitDao.selectVisitListByDate(map);
    }


    @Override
    public List<Visit> selectLikeVisitListByPage(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return visitDao.selectLikeVisitListByPage(map);
    }

    @Override
    public List<?> selectVisitListByIp(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return visitDao.selectVisitListByIp(map);
    }

    @Override
    public List<?> selectLikeVisitListGroupByIp(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return visitDao.selectLikeVisitListGroupByIp(map);
    }
}
