package cn.rain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * description:
 *
 * @author 任伟
 * @date 2018/5/2 14:58
 */
@Controller
public class LoginController {

    /**
     * 我们在这里使用@PostMapping(value = "/user/login")来简化@RequestMapping(value = "/user/login", method = RequestMethod.POST)，
     * 我们可以点进该主键查看，它其实就是封装了@RequestMapping，并且它将method定义为RequestMethod.POST，因为我们进行合理的猜想，
     * 应该还封装其他请求方式的简化注解，如@GetMapping、@PutMapping、@DeleteMapping等，验证后发现确实如此。
     *
     * @param username 用户登录时的用户名请求参数，使用 @RequestParam("username")注解，要求请求中必须包含username参数，不然会报错。
     * @param password 用户登录时的密码请求参数，使用 @RequestParam("password")注解，要求请求中必须包含password参数，不然会报错。
     * @param map 用于封装登录失败后的错误信息。
     * @param session 已登录用户的用户名会放到session域中，为了让拦截器拦截未登录用户。
     * @return 登录成功重定向到main.html（Thymeleaf会将该视图映射到dashboard.html）；登录失败回到登录页面（即login.html）。
     */
    @PostMapping(value = "/user/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        Map<String, Object> map,
                        HttpSession session
                        ){

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            // 登录成功，将username放入session域中。
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
