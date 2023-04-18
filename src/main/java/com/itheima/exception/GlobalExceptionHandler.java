package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //表示该类是一个异常处理类，该类中的方法处理完异常之后响应json到客户端
public class GlobalExceptionHandler {

    //表示该方法处理Exception及其子类异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception e){   //参数：接收程序抛出的异常对象
        //打印异常信息、也可以将异常信息保存到数据库中，通知开发人员。
        log.info("异常信息：{}",e.getMessage());
        //返回友好提示信息给客户看
        return Result.error("服务器正在开小差，呜呜呜~~~");
    }
}
