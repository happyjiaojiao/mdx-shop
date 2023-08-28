package com.mdx.user.service.impl;

import com.mdx.common.exception.BizException;
import com.mdx.common.manager.RedisManager;
import com.mdx.common.utils.JWTProvider;
import com.mdx.common.utils.StringUtils;
import com.mdx.user.constant.UserConstant;
import com.mdx.user.dto.MdxUserDTO;
import com.mdx.user.entity.MdxUser;
import com.mdx.user.feign.OrderFeign;
import com.mdx.user.feign.ProductFeign;
import com.mdx.user.repository.MdxUserRepository;
import com.mdx.user.service.UserService;
import com.mdx.user.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private MdxUserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private RedisManager redisManager;

    @Value("${jwt.prefix}")
    private String prefix;

    /**
     * 登录
     * @param mdxUserDTO
     * @return
     */
    @Override
    public LoginVo login(MdxUserDTO mdxUserDTO) {
        MdxUser mdxUser = userRepository.findByUserName(mdxUserDTO.getUserName());
        if (mdxUser == null){
            throw new BizException("用户不存在");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 判断用户名密码是否正确
        if (StringUtils.isEmpty(mdxUser.getUserName()) ||
                ! encoder.matches(mdxUserDTO.getPassword(), mdxUser.getPassword())){
            throw new BizException("用户名或者密码错误");
        }
        // 生成token
        String token = jwtProvider.generateToken(mdxUser.getUserName());

        // 将token存入redis
        redisManager.set(UserConstant.USER_TOKEN_KEY_REDIS + mdxUser.getUserName(),token,604800);

        return LoginVo.builder()
                .userId(mdxUser.getUserId().toString())
                .userName(mdxUser.getUserName())
                .token(prefix + " " + token).build();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("abcd12345");
        System.out.println(password);
    }

    @Override
    public String getOrderNo(String userId) {
        return orderFeign.getOrderNo(userId);
    }


    @Override
    public String allProduct(){ return productFeign.allProduct();}

}
