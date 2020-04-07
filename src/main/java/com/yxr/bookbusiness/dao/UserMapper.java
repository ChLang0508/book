package com.yxr.bookbusiness.dao;

import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.tools.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long ord);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long ord);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPhone(@Param("phone") String phone);

    List<User> getList(@Param("pager")Pager pager,@Param("user")User user);

    int getListCount(@Param("user")User user);
}