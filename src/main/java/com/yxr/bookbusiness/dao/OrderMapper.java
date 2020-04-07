package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.Order;
import com.yxr.bookbusiness.mode.Order;
import com.yxr.bookbusiness.tools.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getList(@Param("pager") Pager pager, @Param("order")Order order);

    int getListCount(@Param("order")Order order);
}