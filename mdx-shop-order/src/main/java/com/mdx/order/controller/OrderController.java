package com.mdx.order.controller;

import com.mdx.common.base.CommonResponse;
import com.mdx.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单接口
     * @param userId
     * @param commodityCode
     * @return
     */
    @PostMapping("createOrder")
    public CommonResponse<String> createOrder(String userId, String commodityCode){
        return CommonResponse.success(orderService.createOrder(userId,commodityCode));
    }

    @GetMapping("getOrderNo")
    public CommonResponse<String> getOrderNo(String userId){
        //System.out.println("Authorization:" + request.getHeader("Authorization"));
        return CommonResponse.success(orderService.getOrderNo(userId));
    }

}
