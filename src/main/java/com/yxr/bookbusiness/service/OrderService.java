package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.dao.OrderDetailMapper;
import com.yxr.bookbusiness.dao.OrderMapper;
import com.yxr.bookbusiness.dao.StockMapper;
import com.yxr.bookbusiness.mode.Order;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private StockMapper stockMapper;

    public Boolean add(Order order) throws Exception {
        return true;
    }

    /**
     * 订单不提供修改
     * @param order
     * @return
     * @throws Exception
     */
    public Boolean update(Order order) throws Exception {
        return false;
    }

    public Boolean del(Order order) throws Exception {
        return true;
    }

    public Pager list(Pager pager, Order order) throws Exception {
        return null;
    }
}
