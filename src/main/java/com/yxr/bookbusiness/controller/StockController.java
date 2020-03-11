package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Resource
    private StockService stockService;
}
