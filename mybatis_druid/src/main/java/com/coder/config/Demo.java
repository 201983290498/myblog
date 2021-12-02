package com.coder.config;

import com.coder.entity.Flower;
import com.coder.mapper.FlowerDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author coder
 * @Date 2021/11/24 22:06
 * @Description
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String context = "mybatis.xml";
        InputStream in = Resources.getResourceAsStream(context);
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        FlowerDao dao = sqlSession.getMapper(FlowerDao.class);
        List<Flower> flowers = dao.selectAll();
        for(Flower each:flowers){
            System.out.println(each);
        }
        sqlSession.commit();
    }
}
