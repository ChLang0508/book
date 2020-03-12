package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.mode.ResponseEntity;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.service.LoginService;
import com.yxr.bookbusiness.service.UserService;
import com.yxr.bookbusiness.tools.Pager;
import com.yxr.bookbusiness.tools.UserTool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;


    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestParam("phone") String phone,
                                   @RequestParam("password") String password) throws Exception {

        User user = loginService.login(phone, password);
        return new ResponseEntity<>(200, true, "登录成功", user);
    }

    @RequestMapping("/admin")
    public ResponseEntity<?> adminLogin(@RequestParam("phone") String phone,
                                        @RequestParam("password") String password) throws Exception {

        User user = loginService.adminLogin(phone, password);
        return new ResponseEntity<>(200, true, "登录成功", user);
    }

    @RequestMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws Exception {
        User user = UserTool.getUser(request);

        Boolean result = loginService.logout(user.getToken());
        if (!result) {
            //退出失败操作
        }
        return new ResponseEntity<>(200, true, "退出登录成功", null);
    }


    @RequestMapping("/signin")
    public ResponseEntity<?> signIn(User user) throws Exception {
        user.setOrd(null);
        user.setBalance(new BigDecimal(0));
        user.setRole(0);
        Boolean result = userService.signIn(user);
        if (result) {
            return new ResponseEntity<>(200, true, "注册成功", null);
        } else {
            return new ResponseEntity<>(400, false, "注册失败", null);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity<?> add(HttpServletRequest request,
                                 @RequestBody User user) throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        Boolean result = userService.signIn(user);
        if (result) {
            return new ResponseEntity<>(200, true, "注册成功", null);
        } else {
            return new ResponseEntity<>(400, false, "注册失败", null);
        }

    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestBody User user) throws Exception {
        User currentUser = UserTool.getUser(request);
        user.setOrd(currentUser.getOrd());
        Boolean result = userService.updateUser(user);
        if (result) {
            return new ResponseEntity<>(200, true, "修改成功", null);
        }
        return new ResponseEntity<>(400, false, "修改失败", null);
    }

    @RequestMapping("/del")
    public ResponseEntity<?> del(HttpServletRequest request,
                                 Long ord) throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        Boolean result = userService.del(ord);
        if (result) {
            return new ResponseEntity<>(200, true, "删除成功", null);
        }
        return new ResponseEntity<>(400, false, "删除失败", null);
    }

    @RequestMapping("/list")
    public ResponseEntity<?> list(User user,
                                  Pager pager,
                                  HttpServletRequest request) throws Exception {

        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        pager = userService.getList(pager, user);
        return new ResponseEntity<>(200, true, "查询成功", pager);
    }

}
