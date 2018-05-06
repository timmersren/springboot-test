package cn.rain.controller;

import cn.rain.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 测试自定义的starter
 * @author 任伟
 * @date 2018/5/7 2:33
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(name);
    }
}
