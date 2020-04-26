package com.yxr.bookbusiness.service;

import com.alibaba.fastjson.JSONArray;
import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.dao.OrderDetailMapper;
import com.yxr.bookbusiness.dao.OrderMapper;
import com.yxr.bookbusiness.dao.StockMapper;
import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.mode.Order;
import com.yxr.bookbusiness.mode.OrderDetail;
import com.yxr.bookbusiness.mode.Stock;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private StockMapper stockMapper;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    public Boolean add(Order order) throws Exception {

        order.setOrderCode(Long.toString(System.currentTimeMillis()));

        if (orderMapper.insertSelective(order) != 1) {
            throw new Exception("单据新增失败，未知错误");
        }
        List<OrderDetail> orderDetailList = JSONArray.parseArray(order.getOrderDetailListStr(), OrderDetail.class);

        BigDecimal totalPrice = new BigDecimal(0);
        int totalNum = 0;

        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderOrd(order.getOrd());
            Book bookTemp = bookMapper.selectByPrimaryKey(orderDetail.getBookOrd());
            if (bookTemp == null) {
                throw new Exception("明细图书不存在");
            }
            Stock stockTemp = stockMapper.selectByBookOrd(orderDetail.getBookOrd());
            if (stockTemp == null || stockTemp.getStockNum() < orderDetail.getBookNum()) {
                throw new Exception("库存不足，请修改订单");
            }
            orderDetail.setBookName(bookTemp.getBookName());
            orderDetail.setISBN(bookTemp.getISBN());
            orderDetail.setBookPress(bookTemp.getPress());
            orderDetail.setBookPrice(bookTemp.getPrice());
            if (orderDetailMapper.insertSelective(orderDetail) != 1) {
                throw new Exception("明细新增失败");
            }
            totalPrice = totalPrice.add(bookTemp.getPrice().multiply(new BigDecimal(orderDetail.getBookNum())));
            totalNum = totalNum + orderDetail.getBookNum();
        }

        Order updateOrder = new Order();
        updateOrder.setOrd(order.getOrd());
        updateOrder.setTotalNum(totalNum);
        updateOrder.setTotalPrice(totalPrice);
        return orderMapper.updateByPrimaryKeySelective(updateOrder) == 1;
    }

    /**
     * 订单不提供修改
     *
     * @param order
     * @return
     * @throws Exception
     */
    public Boolean update(Order order) throws Exception {
        return false;
    }

    public Boolean del(Order order) throws Exception {
        Order orderTemp = orderMapper.selectByPrimaryKey(order.getOrd());
        if (orderTemp == null) {
            throw new Exception("选择删除的条目不存在");
        }
        if (orderTemp.getUserOrd() != order.getUserOrd()) {
            throw new Exception("越权操作");
        }
        orderDetailMapper.delByOrderOrd(order.getOrd());
        return orderMapper.deleteByPrimaryKey(order.getOrd()) == 1;
    }

    public Pager list(Pager pager, Order order) throws Exception {
        List<Order> list = orderMapper.getList(pager, order);
        for (Order orderTemp : list) {
            List<OrderDetail> orderDetails = orderDetailMapper.getListByOrderId(orderTemp.getOrd());
            orderTemp.setOrderDetailList(orderDetails);
        }
        pager.setList(list);
        pager.setTotalRow(orderMapper.getListCount(order));
        return pager;
    }

    public Order getOrderByID(Long orderID) throws Exception {
        Order order = orderMapper.selectByPrimaryKey(orderID);
        if (order == null) {
            throw new Exception("订单不存在");
        }
        List<OrderDetail> orderDetailList = orderDetailMapper.getListByOrderId(orderID);
        order.setOrderDetailList(orderDetailList);
        return order;
    }
}
