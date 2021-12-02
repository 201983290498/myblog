package com.coder.entity;

import org.apache.ibatis.type.Alias;

/**
 * @Author coder
 * @Date 2021/11/24 21:53
 * @Description
 */
@Alias("flower")
public class Flower {
    private Integer id;
    private String flowerName;
    private double flowerPrice;

    public Flower() {
    }

    public Flower(Integer id, String flowerName, double flowerPrice) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerPrice = flowerPrice;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", flowerName='" + flowerName + '\'' +
                ", flowerPrice=" + flowerPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public double getFlowerPrice() {
        return flowerPrice;
    }

    public void setFlowerPrice(double flowerPrice) {
        this.flowerPrice = flowerPrice;
    }
}
