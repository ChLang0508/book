package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.BookClass;

public interface BookClassMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(BookClass record);

    int insertSelective(BookClass record);

    BookClass selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(BookClass record);

    int updateByPrimaryKey(BookClass record);
}