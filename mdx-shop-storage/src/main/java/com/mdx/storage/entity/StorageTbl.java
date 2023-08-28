package com.mdx.storage.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class StorageTbl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String commodityCode;

    private String commodityName;

    private Integer price;

    private Integer count;
}
