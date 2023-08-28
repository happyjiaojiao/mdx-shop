package com.mdx.storage.service;


public interface StorageService {

    /**
     * 扣除存储数量
     */
    String deduct(String commodityCode, int count);

    /**
     * 查看所有商品
     */
    String allProduct();
}
