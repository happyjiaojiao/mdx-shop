package com.mdx.order.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mdx-shop-storage")
@Component
public interface StorageFeign {

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     * @return
     */
    @GetMapping("storage/deduct")
    ResponseEntity<String> deduct(@RequestParam String commodityCode, @RequestParam Integer count);

}
