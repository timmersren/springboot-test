package cn.rain.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * description: 定义ErrorAttributes接口的实现，就会替换掉给容器中注册的默认的DefaultErrorAttributes。
 * 因为由于@ConditionalOnMissingBean(value = ErrorAttributes.class)，如果容器中没有ErrorAttributes接口
 * 实现的话，ErrorMvcAutoConfiguration就会给容器注册一个默认的DefaultErrorAttributes。
 * 所以我们自己来实现一个，不用默认的DefaultErrorAttributes。
 *
 * @author 任伟
 * @date 2018/5/4 10:49
 */

@Component // 需要将该自定义组件加在容器中才能起作用
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        // 我们调用父类DefaultErrorAttributes.getErrorAttributes()方法返回的Map就是我们不定义该组件情况下默认返回的那些错误数据，
        // map中包含timestamp、status、error、path等
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        // 我们可以在这个map的基础上进行错误信息的自定义，比如remove掉某个属性，或者添加一些我们想要的属性，如下
        map.remove("status");
        map.put("name", "任伟");
        // 获取异常处理器存入request域中的map（错误数据信息）
        Map<String, Object> extMap = (Map<String, Object>) requestAttributes.getAttribute("extMap", 0);
        // 将异常处理器中的extMap和本方法里的map进行合并
        map.putAll(extMap);
        return map;
    }
}
