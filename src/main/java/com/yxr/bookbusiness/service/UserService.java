package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.UserMapper;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    public Boolean signIn(User user) throws Exception {
        User temp = userMapper.selectByPhone(user.getPhone());
        if (temp != null) {
            throw new Exception("号码已被注册");
        }
        user.setCreateTime(new Date());
        //md5加密
        user.setPass(DigestUtils.md5DigestAsHex(user.getPass().getBytes()));
        return userMapper.insertSelective(user) == 1;
    }

    public Boolean updateUser(User user) {
        user.setCreateTime(null);
        user.setRole(null);
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    public Boolean del(Long ord){
        return userMapper.deleteByPrimaryKey(ord)==1;
    }

    public Pager getList(Pager pager,User user){
        List<User> list = userMapper.getList(pager, user);
        pager.setList(list);
        pager.setTotalRow(userMapper.getListCount(user));
        return pager;
    }
}
