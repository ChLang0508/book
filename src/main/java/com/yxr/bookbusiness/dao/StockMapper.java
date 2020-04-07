package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.Stock;
import com.yxr.bookbusiness.mode.Stock;
import com.yxr.bookbusiness.tools.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    List<Stock> getList(@Param("pager") Pager pager, @Param("stock")Stock stock);

    int getListCount(@Param("stock")Stock stock);

    Stock selectByBookOrd(@Param("bookOrd")Long bookOrd);
}