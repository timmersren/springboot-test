package cn.rain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * description:
 *
 * @author 任伟
 * @date 2018/4/30 19:21
 */
@Controller
public class HelloController {

    /**
     * 当我们访问http://localhost:8080/ 即该项目时，由于spring boot默认会访问静态资源下的index页面，即public/index.html。
     * 如果我们想默认访问模板中的login页面，需要写一个方法，让该方法的返回值让Thymeleaf模板引擎来解析并返回到模板页面，
     * 即templates下的login页面。另外还可以通过扩展SpringMVC的方式实现，见HelloController。
     */
//    @RequestMapping(value = {"/", "/login.html"})
//    public String index(){
//        // 让Thymeleaf去解析返回值，前后拼串。前面拼上"/templates/"，后面拼上".html"。
//        return "index";
//    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world !";
    }


    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        // 模拟查出一些数据，通过Thymeleaf渲染再页面显示
        map.put("hello","<h1>你好</h1>");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        // thymeleaf会自动去classpath：templates路径下去寻找success.html。
        return "success";
    }

}
