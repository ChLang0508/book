package com.yxr.bookbusiness.service;

import com.yxr.bookbusiness.dao.BookClassMapper;
import com.yxr.bookbusiness.dao.BookMapper;
import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.tools.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookClassMapper bookClassMapper;


    public Boolean add(Book book) throws Exception {
        return true;
    }

    public Boolean update(Book book) throws Exception {
        return true;
    }

    public Boolean del(Long ord) throws Exception {
        return true;
    }

    public Pager list(Pager pager, Book book) throws Exception {
        return null;
    }
}
