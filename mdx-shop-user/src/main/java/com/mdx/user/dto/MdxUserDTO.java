package com.mdx.user.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;



@Data
public class MdxUserDTO {
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 密码
    */
    private String password;
    /**
    * 昵称
    */
    private String nick;
    /**
    * 手机号
    */
    private String phone;
    /**
    * 电子邮件
    */
    private String email;
    /**
    * 状态 0 启用 1禁用
    */
    private Integer status;
    /**
    * 性别 0 男 1 女
    */
    private Integer sex;
    /**
    * 个人描述
    */
    private String remarks;
    /**
    * 上次登录时间
    */
    private Date lastLoginTime;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;
}