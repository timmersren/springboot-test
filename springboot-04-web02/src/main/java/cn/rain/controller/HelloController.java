package cn.rain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description:
 *
 * @author 任伟
 * @date 2018/5/5 2:13
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg", "你好");
        return "success";
    }

}
