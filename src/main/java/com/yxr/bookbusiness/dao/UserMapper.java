package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}