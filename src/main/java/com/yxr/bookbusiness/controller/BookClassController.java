package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.service.BookClassService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bookclass")
public class BookClassController {

    @Resource
    private BookClassService bookClassService;
}
