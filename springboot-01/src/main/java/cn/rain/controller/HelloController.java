package cn.rain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: spring boot入门程序的controller
 * @author 任伟
 * @date 2018/4/1 14:30
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello world";
    }
}
