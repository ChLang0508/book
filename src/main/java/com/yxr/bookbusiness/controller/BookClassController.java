package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.mode.BookClass;
import com.yxr.bookbusiness.mode.ResponseEntity;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.service.BookClassService;
import com.yxr.bookbusiness.tools.UserTool;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图书类别
 */

@RestController
@CrossOrigin
@RequestMapping("/bookclass")
public class BookClassController {

    @Resource
    private BookClassService bookClassService;

    @RequestMapping("/list")
    public ResponseEntity<?> list(BookClass bookClass) {
        List<BookClass> list = bookClassService.list(bookClass);
        return new ResponseEntity<>(200, true, "查询成功", list);
    }

    @RequestMapping("/add")
    public ResponseEntity<?> add(HttpServletRequest request,
                                 @RequestBody BookClass bookClass) throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        if (StringUtils.isEmpty(bookClass.getClassName())) {
            return new ResponseEntity<>(400, false, "类别名称不能为空", null);
        }
        bookClass.setOrd(null);
        Boolean result = bookClassService.add(bookClass);

        if (result) {
            return new ResponseEntity<>(200, true, "新增成功", null);
        } else {
            return new ResponseEntity<>(400, false, "新增失败", null);
        }
    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestBody BookClass bookClass) throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        if (bookClass.getOrd() == null) {
            return new ResponseEntity<>(400, false, "请选择需要修改的条目", null);
        }

        Boolean result = bookClassService.update(bookClass);

        if (result) {
            return new ResponseEntity<>(200, true, "修改成功", null);
        } else {
            return new ResponseEntity<>(400, false, "修改失败", null);
        }
    }


    @RequestMapping("/del")
    public ResponseEntity<?> del(HttpServletRequest request,
                                 Long ord) throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }

        Boolean result = bookClassService.del(ord);

        if (result) {
            return new ResponseEntity<>(200, true, "删除成功", null);
        } else {
            return new ResponseEntity<>(400, false, "删除失败", null);
        }

    }


}
