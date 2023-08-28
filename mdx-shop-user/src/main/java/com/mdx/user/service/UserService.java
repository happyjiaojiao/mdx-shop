package com.mdx.user.service;

import com.mdx.user.dto.MdxUserDTO;
import com.mdx.user.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 登录
     * @param mdxUserDTO
     * @return
     */
    LoginVo login(MdxUserDTO mdxUserDTO);

    String getOrderNo(String userId);

    String allProduct();
}
