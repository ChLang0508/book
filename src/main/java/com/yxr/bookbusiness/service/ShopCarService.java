package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.dao.ShopCarMapper;
import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.mode.ShopCar;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopCarService {

    @Resource
    private ShopCarMapper shopCarMapper;
    @Resource
    private BookMapper bookMapper;

    public Boolean add(ShopCar shopCar) throws Exception {
        Book temp = bookMapper.selectByPrimaryKey(shopCar.getBookId());
        if (temp == null) {
            throw new Exception("图书信息不存在");
        }
        ShopCar shopCar1 = shopCarMapper.getByUserAndBook(shopCar.getUserId(), shopCar.getBookId());
        if (shopCar1 == null) {
            return shopCarMapper.insertSelective(shopCar) == 1;
        } else {
            shopCar.setOrd(shopCar1.getOrd());
            shopCar.setNum(shopCar1.getNum() + shopCar.getNum());
            return shopCarMapper.updateByPrimaryKeySelective(shopCar) == 1;
        }

    }

    public Boolean update(ShopCar shopCar) throws Exception {
        ShopCar shopCarTemp = shopCarMapper.selectByPrimaryKey(shopCar.getOrd());
        if (shopCarTemp == null) {
            throw new Exception("选择修改的条目不存在");
        }

        if (shopCar.getBookId() != null) {
            Book temp = bookMapper.selectByPrimaryKey(shopCar.getBookId());
            if (temp == null) {
                throw new Exception("图书信息不存在");
            }
        }
        return shopCarMapper.updateByPrimaryKeySelective(shopCar) == 1;
    }

    public Boolean del(ShopCar shopCar) throws Exception {
        ShopCar shopCarTemp = shopCarMapper.selectByPrimaryKey(shopCar.getOrd());
        if (shopCarTemp == null) {
            throw new Exception("选择删除的条目不存在");
        }
        if (shopCarTemp.getUserId() != shopCar.getUserId()) {
            throw new Exception("越权操作");
        }
        return shopCarMapper.deleteByPrimaryKey(shopCar.getOrd()) == 1;
    }

    public Pager list(Pager pager, ShopCar shopCar) throws Exception {
        List<Map<String, Object>> list = shopCarMapper.getList(pager, shopCar);
        pager.setList(list);
        pager.setTotalRow(shopCarMapper.getListCount(shopCar));
        return pager;
    }
}
