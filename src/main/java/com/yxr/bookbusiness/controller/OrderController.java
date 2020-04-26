package com.yxr.bookbusiness.controller;


import com.yxr.bookbusiness.mode.Order;
import com.yxr.bookbusiness.mode.ResponseEntity;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.service.OrderService;
import com.yxr.bookbusiness.tools.Pager;
import com.yxr.bookbusiness.tools.UserTool;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 订单系统
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/list")
    public ResponseEntity<?> list(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Pager pager,
                                  Order order) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        if (currentUser.getRole() != 1) {
            order.setUserOrd(currentUser.getOrd());
        }
        pager = orderService.list(pager, order);
        return new ResponseEntity<>(200, true, "查询成功", pager);
    }

    @RequestMapping("/getorder")
    public ResponseEntity<?> list(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Long orderID) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        Order order = orderService.getOrderByID(orderID);
        if (!order.getUserOrd().equals(currentUser.getOrd())) {
            return new ResponseEntity<>(401, false, "订单不存在", null);
        }
        return new ResponseEntity<>(200, true, "查询成功", order);


    }

    @RequestMapping("/add")
    public ResponseEntity<?> add(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody Order order) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        if (order.getOrderDetailListStr() == null) {
            return new ResponseEntity<>(400, false, "订单明细不能为空", null);
        }

        order.setOrd(null);
        order.setCreateTime(new Date());
        order.setUserOrd(currentUser.getOrd());
        order.setStatus(1);

        Boolean result = orderService.add(order);
        if (result) {
            return new ResponseEntity<>(200, true, "新增成功", null);
        } else {
            return new ResponseEntity<>(400, false, "新增失败", null);
        }
    }

    /**
     * 预留接口，不提供订单修改
     *
     * @param request
     * @param order
     * @return
     */
    @RequestMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestBody(required = false) Order order) {
        return null;
    }


    @RequestMapping("/del")
    public ResponseEntity<?> del(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Order order) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        if (currentUser.getRole() != 1) {
            order.setUserOrd(currentUser.getOrd());
        }
        if (order.getOrd() == null) {
            return new ResponseEntity<>(400, false, "请选择需要删除的条目", null);
        }
        Boolean result = orderService.del(order);

        if (result) {
            return new ResponseEntity<>(200, true, "删除成功", null);
        } else {
            return new ResponseEntity<>(400, false, "删除失败", null);
        }
    }
}
