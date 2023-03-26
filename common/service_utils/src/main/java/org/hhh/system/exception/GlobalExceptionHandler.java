package org.hhh.system.exception;

import org.hhh.common.result.Result;
import org.hhh.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 全局异常处理类
 *
 */

@ControllerAdvice //AOP
public class GlobalExceptionHandler {

    //全局异常
    @ExceptionHandler(Exception.class)//指定异常类型为所有异常
    @ResponseBody//指定返回数据为json格式
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }
    //特定异常
    @ExceptionHandler(ArithmeticException.class)//指定异常类型为特定异常
    @ResponseBody//指定返回数据为json格式
    public Result error(ArithmeticException e){

        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }

    //自定义异常
    @ExceptionHandler(PersonalException.class)//指定异常类型为特定异常
    @ResponseBody//指定返回数据为json格式
    public Result error(PersonalException e){

        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前功能的操作权限");
    }
}
