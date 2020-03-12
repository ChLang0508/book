package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.dao.StockMapper;
import com.yxr.bookbusiness.mode.Stock;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockService {

    @Resource
    private StockMapper stockMapper;
    @Resource
    private BookMapper bookMapper;

    public Boolean add(Stock stock) throws Exception {
        return true;
    }

    public Boolean update(Stock stock) throws Exception {
        return true;
    }

    public Boolean del(Long ord) throws Exception {
        return true;
    }

    public Pager list(Pager pager, Stock stock) throws Exception {
        return null;
    }

}
