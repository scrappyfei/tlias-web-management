package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

//记录操作员工管理增删改的日志信息
@Component
@Aspect //表示该类是一个切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")  //哪些方法上使用@Log注解，这些方法就会被增强
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {
        //1 private Integer operateUser; //操作人ID
        String jwt = request.getHeader("token");
        //校验jwt令牌，获取员工id
        Claims claims = JwtUtils.parseJWT(jwt);  //有效载荷（存储了自定义信息，也就是 员工信息）
        Integer operateUser = (Integer) claims.get("id");

        //2 private LocalDateTime operateTime; //操作时间
        LocalDateTime operateTime=LocalDateTime.now();

        //3 private String className; //操作类名
        String className=pjp.getTarget().getClass().getName();

        //4 private String methodName; //操作方法名
        String methodName = pjp.getSignature().getName();

        //5 private String methodParams; //操作方法参数
        Object[] args = pjp.getArgs();
        String methodParams = objectMapper.writeValueAsString(args);  //转成json

        //记录开始时间
        long start = System.currentTimeMillis();
        //6 private String returnValue; //操作方法返回值
        Object result = pjp.proceed();  //执行目标方法，返回值为目标方法的返回值
        String returnValue = objectMapper.writeValueAsString(result);  //转成json

        //7 private Long costTime; //操作耗时
        long end = System.currentTimeMillis();
        Long costTime=end-start;

        //8 封装以上信息到OperateLog对象中，调用OperateLogMapper方法，保存日志信息
        OperateLog operateLog=new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        return result;
    }
}
