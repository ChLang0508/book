package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}