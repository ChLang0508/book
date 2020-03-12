package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.BookClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookClassMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(BookClass record);

    int insertSelective(BookClass record);

    BookClass selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(BookClass record);

    int updateByPrimaryKey(BookClass record);

    List<BookClass> getList(@Param("bookClass")BookClass bookClass);
}