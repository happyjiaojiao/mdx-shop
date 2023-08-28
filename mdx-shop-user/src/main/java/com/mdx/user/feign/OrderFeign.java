package com.mdx.user.feign;

import com.mdx.user.handler.OrderFeignHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "mdx-shop-order",fallback = OrderFeignHandler.class)
@Component
public interface OrderFeign {
    @GetMapping("order/getOrderNo")
    String getOrderNo(@RequestParam String userId);

}
