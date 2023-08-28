package com.mdx.user.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mdx.common.base.CommonResponse;
import com.mdx.user.dto.MdxUserDTO;
import com.mdx.user.handler.UserSentinelResourceHandler;
import com.mdx.user.service.UserService;
import com.mdx.user.vo.LoginVo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * 熔断规则机制
     * @param mdxUserDTO
     * @return
     */
    @PostMapping("login")
    @SentinelResource(value = "hotspotResource" , blockHandler = "hotspotResource",
            blockHandlerClass = UserSentinelResourceHandler.class)
    public CommonResponse<LoginVo> login(@RequestBody MdxUserDTO mdxUserDTO){
        return CommonResponse.success(userService.login(mdxUserDTO));
    }


    /**
     * 测试openFeign获取订单服务接口
     * @param userId
     * @return
     */
    @GetMapping("getOrderNo")
    @SentinelResource(value = "getOrderNoResource",blockHandler = "getOrderNoBlockHandler",
            blockHandlerClass = UserController.class)
    public CommonResponse<String> getOrderNo(String userId){
        return CommonResponse.success(userService.getOrderNo(userId));
    }


    /**
     * 测试openFeign获取库存服务接口
     * @return
     */

    @GetMapping("allProduct")
    @SentinelResource(value = "sentinelAResource" , fallback = "sentinelAResource",
            fallbackClass = UserSentinelResourceHandler.class)
    public CommonResponse<String> allProduct(){
        return CommonResponse.success(userService.allProduct());
    }



    /**
     * 限流后续操作方法
     * @param
     * @return
     */
    public static CommonResponse<String> getOrderNoBlockHandler(String userId,BlockException e){
        String msg = "不好意思，前方拥挤，请您稍后再试";
        return CommonResponse.success(msg);
    }

}
