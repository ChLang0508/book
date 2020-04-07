package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.mode.ResponseEntity;
import com.yxr.bookbusiness.mode.Stock;
import com.yxr.bookbusiness.mode.User;
import com.yxr.bookbusiness.service.StockService;
import com.yxr.bookbusiness.tools.Pager;
import com.yxr.bookbusiness.tools.UserTool;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 库存
 */
@RestController
@CrossOrigin
@RequestMapping("/stock")
public class StockController {
    @Resource
    private StockService stockService;

    @RequestMapping("/list")
    public ResponseEntity<?> list(HttpServletRequest request,
                                  Pager pager,
                                  Stock stock) throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        pager=stockService.list(pager, stock);
        return new ResponseEntity<>(200, true, "查询成功", pager);
    }

    @RequestMapping("/add")
    public ResponseEntity<?> add(HttpServletRequest request,
                                 @RequestBody Stock stock) throws Exception{
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }
        stock.setOrd(null);

        Boolean result=stockService.add(stock);
        if (result) {
            return new ResponseEntity<>(200, true, "新增成功", null);
        } else {
            return new ResponseEntity<>(400, false, "新增失败", null);
        }

    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestBody Stock stock)throws Exception {
        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }

        if (stock.getOrd() == null) {
            return new ResponseEntity<>(500, false, "请选择需要修改的条目", null);
        }

        Boolean result = stockService.update(stock);

        if (result) {
            return new ResponseEntity<>(200, true, "修改成功", null);
        } else {
            return new ResponseEntity<>(400, false, "修改失败", null);
        }
    }


    @RequestMapping("/del")
    public ResponseEntity<?> del(HttpServletRequest request,
                                 Long ord) throws Exception{

        User currentUser = UserTool.getUser(request);
        if (currentUser.getRole() != 1) {
            return new ResponseEntity<>(401, false, "权限不足", null);
        }

        Boolean result = stockService.del(ord);

        if (result) {
            return new ResponseEntity<>(200, true, "删除成功", null);
        } else {
            return new ResponseEntity<>(400, false, "删除失败", null);
        }
    }
}
