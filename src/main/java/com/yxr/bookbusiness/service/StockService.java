package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.dao.StockMapper;
import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.mode.Stock;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockService {

    @Resource
    private StockMapper stockMapper;
    @Resource
    private BookMapper bookMapper;

    public Boolean add(Stock stock) throws Exception {
        Book temp = bookMapper.selectByPrimaryKey(stock.getBookOrd());
        if (temp == null) {
            throw new Exception("图书信息不存在");
        }
        return stockMapper.insertSelective(stock) == 1;
    }

    public Boolean update(Stock stock) throws Exception {
        Stock stockTemo = stockMapper.selectByPrimaryKey(stock.getOrd());
        if (stockTemo == null) {
            throw new Exception("选择修改的条目不存在");
        }

        if (stock.getBookOrd() != null) {
            Book temp = bookMapper.selectByPrimaryKey(stock.getBookOrd());
            if (temp == null) {
                throw new Exception("图书信息不存在");
            }
        }
        return stockMapper.updateByPrimaryKeySelective(stock) == 1;
    }

    public Boolean del(Long ord) throws Exception {
        Stock stock = stockMapper.selectByPrimaryKey(ord);
        if (stock == null) {
            throw new Exception("选择删除的条目不存在");
        }
        return stockMapper.deleteByPrimaryKey(ord) == 1;
    }

    public Pager list(Pager pager, Stock stock) throws Exception {
        List<Stock> list = stockMapper.getList(pager, stock);
        pager.setList(list);
        pager.setTotalRow(stockMapper.getListCount(stock));
        return pager;
    }

}
