package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookClassMapper;
import com.yxr.bookbusiness.mode.BookClass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookClassService {

    @Resource
    private BookClassMapper bookClassMapper;

    public Boolean add(BookClass bookClass) throws Exception {
        return bookClassMapper.insertSelective(bookClass) == 1;
    }

    public Boolean update(BookClass bookClass) throws Exception {
        if (bookClassMapper.selectByPrimaryKey(bookClass.getOrd()) == null) {
            throw new Exception("选择的条目不存在");
        }
        return bookClassMapper.updateByPrimaryKeySelective(bookClass) == 1;
    }

    public Boolean del(Long ord) throws Exception {
        if (bookClassMapper.selectByPrimaryKey(ord) == null) {
            throw new Exception("选择的条目不存在");
        }
        return bookClassMapper.deleteByPrimaryKey(ord) == 1;
    }

    public List<BookClass> list(BookClass bookClass) {
        return bookClassMapper.getList(bookClass);
    }
    public BookClass getBookClass(Long bookClass) {
        return bookClassMapper.selectByPrimaryKey(bookClass);
    }

}
