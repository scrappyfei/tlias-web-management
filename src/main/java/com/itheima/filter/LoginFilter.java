package com.itheima.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter("/*")  //拦截所有请求
public class LoginFilter implements Filter {
    @Autowired
    private ObjectMapper objectMapper; //spring底层转json使用的对象

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //1 获取请求url
        String url = request.getRequestURL().toString();  // 得到：http://localhost:8080/emps
        //String uri = request.getRequestURI();  // 得到：/emps
        //2 判断请求url是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")){
            filterChain.doFilter(request,response);
            return;  //必须return阻止代码继续往下执行。
        }
        //3 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");
        //4 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasText(jwt)){
            //响应未登录的json
            Result result = Result.error("NOT_LOGIN");
            //将参数二result对象转换成json字符串，通过参数一响应输出发送给客户端
            objectMapper.writeValue(response.getWriter(),result);
            return;  //必须return阻止代码继续往下执行。
        }
        //5 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
            //6 放行
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常就说明校验失败
            //响应未登录的json
            Result result = Result.error("NOT_LOGIN");
            //将参数二result对象转换成json字符串，通过参数一响应输出发送给客户端
            objectMapper.writeValue(response.getWriter(),result);
            return;  //必须return阻止代码继续往下执行。
        }
    }
}
