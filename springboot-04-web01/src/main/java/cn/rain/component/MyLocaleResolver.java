package cn.rain.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * description: 编写自己的区域信息解析器，不是用spring boot默认的（根据请求头Accept-Language）来获取。
 * 自己的区域信息解析器可以通过点击页面最下方的超链接来改变中英文。
 *
 * @author 任伟
 * @date 2018/5/2 14:32
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 如果点击超链接，会带上请求参数l，因此先获取request中的参数l
        String param = request.getParameter("l");
        // 如果没有该参数就使用系统默认的Locale
        Locale locale = Locale.getDefault();
        // 如果参数不为空，那么就用获取到的参数创建一个Locale对象并返回
        if(!StringUtils.isEmpty(param)){
            String[] split = param.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
