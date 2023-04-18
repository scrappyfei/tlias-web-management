package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    /**
     * 处理登录的请求
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        //2 调用service层方法，进行登录
        Emp e=empService.login(emp);
        //判断是否登录成功，如果登录成功，就生成jwt令牌信息，并返回给客户端
        if(e!=null){
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            claims.put("name",e.getName());
            //生成jwt令牌信息
            String jwt = JwtUtils.generateJwt(claims);
            //返回给客户端
            return Result.success(jwt);
        }
        //3 响应登录失败的Result
        return Result.error("用户名或者密码错误(╯﹏╰)!");
    }
}
