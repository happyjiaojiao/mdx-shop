package com.mdx.storage.service.impl;
import com.alibaba.fastjson.JSON;
import com.mdx.common.exception.BizException;
import com.mdx.common.utils.StringUtils;
import com.mdx.storage.entity.StorageTbl;
import com.mdx.storage.repository.StorageRepository;
import com.mdx.storage.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import javax.annotation.Resource;
import javax.json.JsonArray;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     */
    @Override
    public String deduct(String commodityCode, int count) {
        //System.out.println("事务id---------------------->" + RootContext.getXID());
        StorageTbl storageTbl = storageRepository.findByCommodityCode(commodityCode);
        if (storageTbl == null){
            return "亲，无此商品！";
        }else if(storageTbl.getCount() == 0){
            return "亲，没有库存了！";
        }else{
            storageTbl.setCount(storageTbl.getCount() - count);
            storageRepository.save(storageTbl);
            return "true";
        }
    }

    /**
     * 查看所有商品
     */
    @Override
    public String allProduct(){
        List<StorageTbl> pro = storageRepository.findAll();
        String msg = "";
        for(int i=0;i<pro.size();i++){
            JsonObject jsonContainer =new JsonObject();
            jsonContainer.addProperty("商品名",pro.get(i).getCommodityName());
            jsonContainer.addProperty("商品id",pro.get(i).getCommodityCode());
            jsonContainer.addProperty("库存量",pro.get(i).getCount());
            jsonContainer.addProperty("价格",pro.get(i).getPrice());
            msg +=  jsonContainer.toString();
        }
        return msg;
    }

}
