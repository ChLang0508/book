package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.Book;

public interface BookMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}