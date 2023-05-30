package com.xiaocai.security.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaocai.common.jwt.JwtHelper;
import com.xiaocai.common.result.ResponseUtil;
import com.xiaocai.common.result.Result;
import com.xiaocai.common.result.ResultCodeEnum;
import com.xiaocai.security.config.RedisCache;
import com.xiaocai.security.custom.CustomUser;
import com.xiaocai.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisTemplate redisTemplate;

    //构造方法
    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            RedisTemplate redisTemplate) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/admin/system/index/login", "POST"));
        this.redisTemplate = redisTemplate;
    }

    //登入认证
    //获取输入的用户名和密码，调用方法完成认证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            //获取用户信息
            LoginVo loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginVo.class);
            //封装对象
            Authentication authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            //调用方法
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //认证成功调用方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        //获取当前用户
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        //生成token字符串
        String token = JwtHelper.createToken(customUser.getSysUser().getId(),
                customUser.getSysUser().getUsername());
        //获取当前用户的权限数据，放到Redis里面 key: username  value: 权限数据
        redisTemplate.opsForValue().set(customUser.getUsername(),
                JSON.toJSONString(customUser.getAuthorities()));
        //返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, Result.ok(map));
    }

    //认证失败调用方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        ResponseUtil.out(response, Result.build(null, ResultCodeEnum.LOGIN_ERROR));
    }
}
