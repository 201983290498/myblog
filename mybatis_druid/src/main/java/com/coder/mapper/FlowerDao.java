package com.coder.mapper;


import com.coder.entity.Flower;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FlowerDao {

//    @Results(id = "flower",value = {
//            @Result(id = true,property = "id",column = "id"),
//            @Result(property = "flowerName", column = "name"),
//            @Result(property = "flowerPrice", column = "price")
//    })
//    @Select("select * from flower")
    List<Flower> selectAll();
}
