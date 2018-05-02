package cn.rain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author 任伟
 * @date 2018/5/2 14:58
 */
@Controller
public class LoginController {

//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping(value = "/user/login") //可以用此注解简化上边那个，不同请求有不同的简化，例如 @GetMapping
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        Map<String, Object> map,
                        HttpSession session
                        ){
        //使用 @RequestParam("xxx")注解，要求请求中必须包含xxx参数，不然会报错。

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            // 已登录的用户会放到session域中，为了让拦截器拦截未登录用户
            session.setAttribute("loginUser", username);
            // 登录成功，为了防止表单重复提交，可以重定向到main.html（已经在MyMvcConfigurer.webMvcConfigurerAdapter()方法
            // 中做了视图映射）
            return "redirect:/main.html";
        }else {
            // 登录失败
            map.put("msg", "用户名或密码错误！");
            return "login";
        }

    }
}
