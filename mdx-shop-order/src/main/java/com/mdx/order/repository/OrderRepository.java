package com.mdx.order.repository;

import com.mdx.order.entity.OrderTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderTbl,Integer> {
    @Override
    List<OrderTbl> findAll();

    /**
     * 获取订单id
     * @param userId
     * @return
     */
    List<OrderTbl> findByuserId(String userId);
}
