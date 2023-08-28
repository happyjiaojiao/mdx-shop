package com.mdx.storage.controller;

import com.mdx.common.base.CommonResponse;
import com.mdx.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService service;

    @GetMapping("/deduct")
    public String deduct(String commodityCode, int count){
        return service.deduct(commodityCode, count);
    }

    @GetMapping("/allProduct")
    public String allProduct(){
        return service.allProduct();
    }
}
