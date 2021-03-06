package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.UserMapper;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.tools.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService {
    private static final Long TTL = (long) 60 * 60 * 24;

    @Autowired
    private RedisUtils redisUtils;
    @Resource
    private UserMapper userMapper;

    public User login(String phone, String password) throws Exception {
        User user = userMapper.selectByPhone(phone);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (!user.getPass().equals(password)) {
            throw new Exception("密码错误");
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        boolean a=redisUtils.set(token, user, TTL);

        return user;
    }

    public User adminLogin(String phone, String password) throws Exception {
        User user = login(phone, password);
        if (user.getRole() != 1) {
            redisUtils.del(user.getToken());
            throw new Exception("权限不足");
        }
        return user;
    }

    public Boolean logout(String token) {
        redisUtils.del(token);
        return true;
    }


}
