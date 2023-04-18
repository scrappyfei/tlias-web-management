package com.itheima.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;
    //在controller方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("preHandle...");
        //1 获取请求url
        //2 判断请求url是否包含login，如果包含，说明是登录操作，放行
        //3 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");
        //4 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasText(jwt)){
            //保存错误信息到Result中
            Result result = Result.error("NOT_LOGIN");
            //将参数二result对象转换成json字符串，通过参数一响应输出发送给客户端
            objectMapper.writeValue(response.getWriter(),result);
            return false; //阻止向下执行
        }
        //5 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
            //6 放行
            return true;  //返回true表示放行，返回false表示阻止
        } catch (Exception e) {
            e.printStackTrace();
            //保存错误信息到Result中
            Result result = Result.error("NOT_LOGIN");
            //将参数二result对象转换成json字符串，通过参数一响应输出发送给客户端
            objectMapper.writeValue(response.getWriter(),result);
            return false; //阻止向下执行
        }
    }
    //在controller方法执行之后，且没有异常时执行
    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }*/

    //在controller方法执行之后，无论是否有异常时都执行
    /*@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion...");
    }*/
}
