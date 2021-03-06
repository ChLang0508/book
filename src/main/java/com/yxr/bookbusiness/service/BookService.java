package com.yxr.bookbusiness.service;

import com.alibaba.fastjson.JSONArray;
import com.yxr.bookbusiness.dao.BookClassMapper;
import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.mode.BookClass;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookClassMapper bookClassMapper;


    public Boolean add(Book book) throws Exception {
        BookClass temp = bookClassMapper.selectByPrimaryKey(book.getBookClass());
        if (temp == null) {
            throw new Exception("图书类别不存在");
        }
        return bookMapper.insertSelective(book) == 1;
    }

    public Book getByID(Long bookID) throws Exception {
        Book book = bookMapper.selectByPrimaryKey(bookID);
        if (book == null) {
            throw new Exception("选择的图书不存在");
        }
        book.setBookClassName(bookClassMapper.selectByPrimaryKey(book.getBookClass()).getClassName());
        return book;
    }

    public List<Book> getByIDs(String bookIDs) throws Exception {
        JSONArray jsonArray = JSONArray.parseArray(bookIDs);
        List<Long> ids = jsonArray.toJavaList(Long.class);
        List<Book> bookList = new ArrayList<>();
        for (Long id : ids) {
            Book book = getByID(id);
            bookList.add(book);
        }
        return bookList;
    }

    public Boolean update(Book book) throws Exception {
        BookClass classTemp = bookClassMapper.selectByPrimaryKey(book.getBookClass());
        if (classTemp == null) {
            throw new Exception("图书类别不存在");
        }
        int result = bookMapper.updateByPrimaryKeySelective(book);
        if (result == 0) {
            throw new Exception("选择的数据不存在");
        }
        return result == 1;
    }

    public Boolean del(Long ord) throws Exception {
        return bookMapper.deleteByPrimaryKey(ord) == 1;
    }

    public Pager list(Pager pager, Book book) throws Exception {
        List<Book> list = bookMapper.getList(pager, book);
        pager.setList(list);
        pager.setTotalRow(bookMapper.getListCount(book));
        return pager;
    }
}
