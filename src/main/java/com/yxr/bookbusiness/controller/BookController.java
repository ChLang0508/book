package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.mode.Book;
import com.yxr.bookbusiness.mode.ResponseEntity;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.service.BookService;
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
import java.util.List;

/**
 * 图书信息
 */
@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/list")
    public ResponseEntity<?> list(Pager pager, Book book) throws Exception {
        pager = bookService.list(pager, book);
        return new ResponseEntity<>(200, true, "查询成功", pager);
    }

    @RequestMapping("/getbook")
    public ResponseEntity<?> getByID(Long bookID) throws Exception {
        Book book = bookService.getByID(bookID);
        return new ResponseEntity<>(200, true, "查询成功", book);
    }

    @RequestMapping("/getbookbyids")
    public ResponseEntity<?> getByIDs(String bookIDs) throws Exception {
        bookIDs = "[" + bookIDs + "]";
        List<Book> book = bookService.getByIDs(bookIDs);
        return new ResponseEntity<>(200, true, "查询成功", book);
    }

    @RequestMapping("/add")
    public ResponseEntity<?> add(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody Book book) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }

        book.setOrd(null);
        book.setCreateTime(new Date());

        Boolean result = bookService.add(book);
        if (result) {
            return new ResponseEntity<>(200, true, "新增成功", null);
        } else {
            return new ResponseEntity<>(400, false, "新增失败", null);
        }
    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Book book) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        if (book.getOrd() == null) {
            return new ResponseEntity<>(500, false, "请选择需要修改的条目", null);
        }

        Boolean result = bookService.update(book);

        if (result) {
            return new ResponseEntity<>(200, true, "修改成功", null);
        } else {
            return new ResponseEntity<>(400, false, "修改失败", null);
        }
    }


    @RequestMapping("/del")
    public ResponseEntity<?> del(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Long ord) throws Exception {
        User currentUser = UserTool.getUser(request, response);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }

        Boolean result = bookService.del(ord);

        if (result) {
            return new ResponseEntity<>(200, true, "删除成功", null);
        } else {
            return new ResponseEntity<>(400, false, "删除失败", null);
        }
    }
}
