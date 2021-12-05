package com.coder.blog.dao;

import com.coder.blog.entity.Image;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * The interface Image dao.
 *
 * @author coder
 */
@CacheNamespace(blocking = true)
public interface ImageDao {

    /**
     * 查找图片
     *
     * @param id 图片id
     * @return 需要查找的图片 image
     */
    @Select("select id, bytes from image where id=#{arg0}")
    Image selectOne(String id);

    /**
     * 查找某张图片是否存在
     *
     * @param id 图片的id
     * @return 返回查找到的图片数量 integer
     */
    @Select("select count(*) from image where id=#{arg0}")
    Integer findExistOne(String id);

    /**
     * 插入图片
     *
     * @param image 待插入的图片
     */
    @Insert("insert into image(id,bytes) values(#{id},#{bytes})")
    void insert(Image image);
}
