package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.ShopCar;

public interface ShopCarMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
}