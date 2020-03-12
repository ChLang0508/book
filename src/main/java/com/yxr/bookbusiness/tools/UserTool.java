package com.yxr.bookbusiness.tools;

import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.tools.redis.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserTool {

    @Resource
    private static RedisUtils redisUtils;

    /**
     * 用于获取当前登录用户，靠请求头部传递的token来获取
     * @param request
     * @return
     * @throws Exception
     */
    public static User getUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new Exception("尚未登录，请登录重新操作");
        }
        User user = (User) redisUtils.get(token);
        if (user == null) {
            throw new Exception("登录信息失效，请重新登录");
        }
        //有操作就延长token的有效时间
        redisUtils.expire(token,(long)60*60*24);
        return user;
    }

}
