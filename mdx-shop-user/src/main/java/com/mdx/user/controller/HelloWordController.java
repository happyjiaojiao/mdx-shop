package com.mdx.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mdx.user.handler.UserSentinelResourceHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RefreshScope
public class HelloWordController {

    @Value("${mdx-shop-user.test.userId}")
    private String userId;

    /**
     * 测试获取nacos配置中心配置
     * @return
     */
    @GetMapping("getUserConfigTest")
    public String getUserConfigTest(){
        return userId != null ? userId : "未获取到配置";
    }

    /**
     * 测试熔断规则机制
     * @return

    @GetMapping("/sentinelA")
    @SentinelResource(value = "sentinelAResource" , fallback = "sentinelAResource", fallbackClass = UserSentinelResourceHandler.class)
    public String sentinelA(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是sentinelA");
        return "我是sentinelA";
    }
     */

    /**
     * 测试centinel热点规则限流
     * @param userId
     * @param shopId
     * @return
     */
    @GetMapping("/hotspot")
    @SentinelResource(value = "hotspotResource" , blockHandler = "hotspotResource", blockHandlerClass = UserSentinelResourceHandler.class)
    public String hotspot(@RequestParam(value = "userId" ,required = false) String userId,
                          @RequestParam(value = "shopId" ,required = false) String shopId){
        System.out.println("我是hotspot");
        return "我是hotspot";
    }
}
