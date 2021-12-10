package com.coder.blog.service.impl;

import com.coder.blog.dao.VisitDao;
import com.coder.blog.entity.visit.Visit;
import com.coder.blog.service.VisitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * The type Visit service imp.
 *
 * @Author coder
 * @Date 2021 /11/26 23:12
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
        return visitDao.insert(record);
    }

    @Override
    public Visit selectByPrimaryKey(Integer id) {
        return visitDao.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(Visit record) {
        return visitDao.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(Visit record) {
        return visitDao.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Visit> selectVisitPageByIp(String ip, Integer page, Integer size) {
      PageHelper.startPage(page,size);
      return new PageInfo<>(visitDao.selectVisitPageByIp(ip));
    }

    @Override
    public Long findVisitTimes(Visit visit) {
        return visitDao.findVisitTimes(visit);
    }

    @Override
    public PageInfo<?> selectVisitListByDate(Map<String, Object> map, int page, int size) {
      PageHelper.startPage(page, size);
      return new PageInfo<>(visitDao.selectVisitListByDate(map));
    }


    @Override
    public PageInfo<Visit> selectLikeVisitListByPage(Map<String, Object> map,int page,int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(visitDao.selectLikeVisitListByPage(map)) ;
    }

  @Override
  public PageInfo<Visit> selectAllByPage(int page, int size) {
    PageHelper.startPage(page,size);
    return new PageInfo<>(visitDao.selectALl());
  }

  @Override
    public PageInfo<?> selectVisitListByIp(Map<String, Object> map,int page,int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(visitDao.selectVisitListByIp(map));
    }

    @Override
    public PageInfo<?> selectLikeVisitListGroupByIp(Map<String, Object> map, int page, int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(visitDao.selectLikeVisitListGroupByIp(map));
    }
}
