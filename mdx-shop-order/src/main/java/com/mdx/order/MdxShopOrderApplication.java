package com.mdx.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableAutoDataSourceProxy
public class MdxShopOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MdxShopOrderApplication.class, args);
    }
}
