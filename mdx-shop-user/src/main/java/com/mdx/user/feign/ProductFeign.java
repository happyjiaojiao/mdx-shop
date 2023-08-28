package com.mdx.user.feign;

import com.mdx.user.handler.OrderFeignHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mdx-shop-storage")
@Component
public interface ProductFeign {
    @GetMapping("storage/allProduct")
     String allProduct();
}