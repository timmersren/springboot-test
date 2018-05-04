package cn.rain.configuration;

import cn.rain.listener.Mylistener;
import cn.rain.servlet.MyServlet;
import cn.rain.filter.MyFilter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * description: 传统的web项目，我们需要在web.xml中进行一些配置，例如注册三大组件等。
 * spring boot最终是打成jar包，因此没有web.xml。我们通过此配置类来讲解传统的web.xml
 * 中的配置在spring boot中是如何配置的。
 *
 * @author 任伟
 * @date 2018/5/4 14:43
 */
@Configuration
public class MyServerConfigurer {
    /**
     * 通过编写嵌入式的Servlet容器的定制器来定制Servlet的相关配置，这种方式配置的Servlet容器和
     * 配置文件中通过 server.xxx = yyy 效果是一样的，因为配置文件中的配置最终其实就是修改
     * ServerProperties类中的属性，而ServerProperties其实就是实现的EmbeddedServletContainerCustomizer接口。
     * 另外要注意，一定要将这个组件加入到容器中才能起作用，因此要加上@Bean注解。
     */
    @Bean
    public EmbeddedServletContainerCustomizer myEmbeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                // container参数就是嵌入式的Servlet容器相关的规则
//                container.setPort(8083);
            }
        };
    }

    /**
     * 给容器中注册ServletRegistrationBean组件，
     * 该组件中包含我们要注册的Servlet以及该Servlet要拦截的路径
     */
    @Bean
    public ServletRegistrationBean myServletRegistrationBean(){
       return new ServletRegistrationBean(new MyServlet(), "/myServlet");
    }

    /**
     * 给容器中注册FilterRegistrationBean组件，
     * 该组件中包含我们要注册的Filter以及要过滤的路径
     */
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 设置要注册的Filter
        registrationBean.setFilter(new MyFilter());
        // 设置要过滤的路径
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    /**
     * 给容器中注册ServletListenerRegistrationBean组件，
     * 该组件中包含我们要注册的Listener
     */
    @Bean
    public ServletListenerRegistrationBean myServletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean<>(new Mylistener());
    }
}
