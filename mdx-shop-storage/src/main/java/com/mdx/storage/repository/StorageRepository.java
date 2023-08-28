package com.mdx.storage.repository;

import com.mdx.storage.entity.StorageTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<StorageTbl,Integer> {

    /**
     * 通过商品code查询库存
     * @param commodityCode
     * @return
     */
    @Query
    StorageTbl findByCommodityCode(String commodityCode);

    /**
     * 查询所有商品
     * @return
     */

    @Override
    List<StorageTbl> findAll();
}
