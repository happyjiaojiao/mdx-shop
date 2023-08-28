package com.mdx.user.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mdx.common.base.CommonResponse;
import com.mdx.user.dto.MdxUserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UserSentinelResourceHandler {
    public static  CommonResponse<String> sentinelAResource(Throwable throwable){
        String msg = "亲，不好意思，您已触发熔断，目前查询服务不可用!";
        return CommonResponse.success(msg);
    }

    public static CommonResponse<String> hotspotResource(@RequestBody MdxUserDTO mdxUserDTO,
                                                         BlockException blockException){
        String msg = "您被认为恶意访问，触发热点限流";
        return CommonResponse.success(msg);
    }
}