package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}