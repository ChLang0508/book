package com.yxr.bookbusiness.controller;

import com.yxr.bookbusiness.service.ShopCarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/shopcar")
public class ShopCarController {
    @Resource
    private ShopCarService shopCarService;
}
