package com.itheima;

import com.itheima.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.itheima.mapper")//加载该包下所有mapper接口，创建代理对象，添加到IOC容器中，相当于每个Mapper接口上写了一个@Mapper注解。
@ServletComponentScan  //扫描servlet相关组件
public class TliasWebManagementApplication implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

    //注册拦截器,参数表示注册器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(loginInterceptor)注册拦截器，
        // .addPathPatterns("/**")指定拦截路径
        // .excludePathPatterns("/login")排除某个路径对应的方法不拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
