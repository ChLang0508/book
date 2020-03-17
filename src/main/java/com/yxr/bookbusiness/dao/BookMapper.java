package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.tools.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> getList(@Param("page") Pager pager, @Param("book")Book book);

    int getListCount(@Param("book")Book book);
}