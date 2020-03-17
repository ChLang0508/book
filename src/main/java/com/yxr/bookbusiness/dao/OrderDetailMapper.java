package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    List<OrderDetail> getListByOrderId(@Param("orderOrd") Long orderOrd);

    int delByOrderOrd(@Param("orderId")Long orderId);
}