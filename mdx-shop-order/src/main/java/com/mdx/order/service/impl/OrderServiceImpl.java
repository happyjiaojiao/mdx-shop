package com.mdx.order.service.impl;

import com.mdx.common.exception.BizException;
import com.mdx.common.utils.StringUtils;
import com.mdx.order.entity.OrderTbl;
import com.mdx.order.feign.StorageFeign;
import com.mdx.order.repository.OrderRepository;
import com.mdx.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.JsonObject;
import javax.annotation.Resource;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private StorageFeign storageFeign;

    /**
     * 下单接口
     * @param userId 用户id
     * @param commodityCode 商品代码
     * @return
     */
    @Override
    @GlobalTransactional
    public String createOrder(String userId, String commodityCode) {
        try {
            System.out.println("事务id---------------------->" + RootContext.getXID());
            //判断是否有此商品

            // 创建订单
            OrderTbl orderTbl = new OrderTbl();
            orderTbl.setUserId(userId);
            orderTbl.setCommodityCode(commodityCode);
            orderTbl.setCount(1); // 假设为1件
            orderTbl.setPrice(10); // 假设为十元

            // 看是否能扣减库存
            ResponseEntity<String> ductmsg = storageFeign.deduct(commodityCode, orderTbl.getCount());
            System.out.println(ductmsg.getBody());
            if (ductmsg.getBody().equals("true")) {
                // 保存订单
                orderRepository.save(orderTbl);
                return "创建订单成功";
            } else {
                throw new BizException(ductmsg.getBody());
            }

        } catch (BizException e) {
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            throw new BizException("创建订单失败");
        }
    }

    @Override
    public String getOrderNo(String userId) {
        String msg = "";
        List<OrderTbl> orderno = orderRepository.findByuserId(userId);
        if (!orderno.isEmpty()){
            for(int i=0; i<orderno.size(); i++){
                JsonObject jsonContainer =new JsonObject();
                jsonContainer.addProperty("订单id",orderno.get(i).getId());
                jsonContainer.addProperty("商品id",orderno.get(i).getCommodityCode());
                jsonContainer.addProperty("数量",orderno.get(i).getCount());
                jsonContainer.addProperty("总价",orderno.get(i).getPrice());
                msg +=  jsonContainer.toString();
            }
            return msg;
        } else {
            return "亲，您好像没有买任何商品哦！您的订单为空！";
        }


/**
        if (StringUtils.isNotEmpty(userId) && userId.equals("liu123")) {
            return "订单号为33";
        } else {
            throw new RuntimeException("单号不存在");
        }
 */
    }
}
