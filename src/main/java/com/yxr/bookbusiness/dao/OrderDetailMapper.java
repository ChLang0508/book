package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}