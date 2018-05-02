package cn.rain.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 实现登录检查的拦截器，没有登录的用户不能访问后台页面。
 *
 * @author 任伟
 * @date 2018/5/2 16:03
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    // 目标方法执行之前进行预检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null){
            // 未登录
            request.setAttribute("msg", "请先登录再访问该页面");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }else {
            // 已登录，直接返回true放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
