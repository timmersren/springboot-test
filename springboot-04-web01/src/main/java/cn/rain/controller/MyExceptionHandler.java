package cn.rain.controller;

import cn.rain.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * description: 自定义返回错误的json数据。
 *
 * @author 任伟
 * @date 2018/5/4 0:42
 */
@SuppressWarnings("all")
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * @param e @ExceptionHandler捕获的异常会传给参数e
     * @return 返回Map的json格式
     */
//    @ResponseBody // 由于我们要返回的json数据，故使用此注解
//    @ExceptionHandler(UserNotExistException.class) // 此注解用于捕获value中定义的异常，value的值是Throwable[]
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//    }

//


    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        // 模拟BasicErrorController中定义状态码的方式来自定义错误的状态码
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", "写点什么好呢...");
        //将map存入到request域中，让MyErrorAttributes在进行错误数据返回获取上面两个字段后将它俩一起返回
        request.setAttribute("extMap", map);
        // 转发到/error，让BasicErrorController进行自适应处理
        return "forward:/error";
    }
}


