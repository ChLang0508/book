package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.mode.ResponseEntity;
import com.yxr.bookbusiness.mode.ShopCar;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.service.ShopCarService;
import com.yxr.bookbusiness.tools.Pager;
import com.yxr.bookbusiness.tools.UserTool;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 购物车
 */
@RestController
@CrossOrigin
@RequestMapping("/shopcar")
public class ShopCarController {
    @Resource
    private ShopCarService shopCarService;

    @RequestMapping("/list")
    public ResponseEntity<?> list(HttpServletRequest request,
                                  Pager pager, ShopCar shopCar) throws Exception {
        User currentUser = UserTool.getUser(request);
        shopCar.setUserId(currentUser.getOrd());
        pager = shopCarService.list(pager, shopCar);
        return new ResponseEntity<>(200, true, "查询成功", pager);
    }

    @RequestMapping("/add")
    public ResponseEntity<?> add(HttpServletRequest request,
                                 @RequestBody ShopCar shopCar) throws Exception {
        User currentUser = UserTool.getUser(request);
        shopCar.setOrd(null);
        shopCar.setCreateTime(new Date());
        shopCar.setUserId(currentUser.getOrd());

        Boolean result = shopCarService.add(shopCar);
        if (result) {
            return new ResponseEntity<>(200, true, "新增成功", null);
        } else {
            return new ResponseEntity<>(400, false, "新增失败", null);
        }
    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestBody ShopCar shopCar) throws Exception {
        User currentUser = UserTool.getUser(request);

        shopCar.setUserId(currentUser.getOrd());
        if (shopCar.getOrd() == null) {
            return new ResponseEntity<>(400, false, "请选择需要修改的条目", null);
        }

        Boolean result = shopCarService.update(shopCar);
        if (result) {
            return new ResponseEntity<>(200, true, "新增成功", null);
        } else {
            return new ResponseEntity<>(400, false, "新增失败", null);
        }
    }


    @RequestMapping("/del")
    public ResponseEntity<?> del(HttpServletRequest request,
                                 ShopCar shopCar) throws Exception {
        User currentUser = UserTool.getUser(request);

        shopCar.setUserId(currentUser.getOrd());

        if (shopCar.getOrd() == null) {
            return new ResponseEntity<>(400, false, "请选择需要删除的条目", null);
        }

        Boolean result = shopCarService.del(shopCar);
        if (result) {
            return new ResponseEntity<>(200, true, "删除成功", null);
        } else {
            return new ResponseEntity<>(400, false, "删除失败", null);
        }
    }
}
