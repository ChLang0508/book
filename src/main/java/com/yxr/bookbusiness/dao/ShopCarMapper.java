package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.ShopCar;
import com.yxr.bookbusiness.mode.ShopCar;
import com.yxr.bookbusiness.tools.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCarMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);

    List<ShopCar> getList(@Param("pager") Pager pager, @Param("shopCar")ShopCar shopCar);

    int getListCount(@Param("shopCar")ShopCar shopCar);
}