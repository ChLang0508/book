package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.dao.ShopCarMapper;
import com.yxr.bookbusiness.mode.ShopCar;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShopCarService {

    @Resource
    private ShopCarMapper shopCarMapper;
    @Resource
    private BookMapper bookMapper;

    public Boolean add(ShopCar shopCar) throws Exception {
        return true;
    }

    public Boolean update(ShopCar shopCar) throws Exception {
        return true;
    }

    public Boolean del(ShopCar shopCar) throws Exception {
        return true;
    }

    public Pager list(Pager pager, ShopCar shopCar) throws Exception {
        return null;
    }
}
