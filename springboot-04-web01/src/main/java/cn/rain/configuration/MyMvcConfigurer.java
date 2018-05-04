package cn.rain.configuration;

import cn.rain.component.LoginHandlerInterceptor;
import cn.rain.component.MyLocaleResolver;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * description: 之前我们使用SpringMVC的时候，为了开启某些功能，我们会在SpringMVC的配置文件中做大量的配置，
 * 诸如拦截器、过滤器、view controller等等。但是使用spring boot后没有了SpringMVC的配置文件，此时我们该如何
 * 开启这些功能呢？官方文档给出了答案，我们需要做的是编写自己的继承了WebMvcConfigurerAdapter的配置类。
 * 就像现在我们正在编写的这个配置类，它继承的WebMvcConfigurerAdapter其实是实现了WebMvcConfigurer接口，
 * WebMvcConfigurerAdapter对接口的实现仅仅是将方法实现为空方法，这样方便我们使用哪个功能重写哪个功能的方法即可。
 *
 * @author 任伟
 * @date 2018/5/2 10:22
 */

//使用@EnableWebMvc将完全接管SpringMVC，使spring boot的默认配置全部失效
//@EnableWebMvc
@Configuration
public class MyMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 这里我们只举例实现视图映射的方法，其他都一样。
        // 效果：浏览器发送/rain 请求，最终会返回成功页面。
        registry.addViewController("/rain").setViewName("success");
    }

    /**
     * 为了让浏览器访问该项目http://localhost:8080/ 能请求到模板中的login.html，我们可以通过在Controller里定义
     * 一个空方法让Thymeleaf映射到模板中的页面。但是我们为了访问到模板页面就在Controller中添加一个空方法实在不是
     * 什么明智的方法，其实我们可以在之前讲解的SpringMVC的扩展功能配置类（即本类）中，实现addViewController("/").setViewName("login")
     * 视图映射方法来让Thymeleaf帮我们解析到模板页面（即上面addViewControllers方法）。
     * 除此之外，假如我们现在仅仅是需要完成一个视图映射功能就单独写一个配置类去继承WebMvcConfigurerAdapter也实在不是
     * 明智之举，其实就算我们不继承WebMvcConfigurerAdapter，仅仅是一个普通的配置类，也可以实现视图映射的功能。
     * 我们知道由于所有的WebMvcConfigurerAdapter组件都会一起起作用，那我们何不直接向容器中添加一个WebMvcConfigurerAdapter？
     * 我们只要让这个组件实现了视图映射的方法不就可以了吗，这需要通过匿名内部类的方式来完成，如下：
     */
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        // 由于所有的WebMvcConfigurerAdapter组件都会一起起作用，那我们就直接向容器中添加一个WebMvcConfigurerAdapter
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                // 添加登录后的视图映射，不添加的话使用的是请求转发，浏览器的请求URL不会改变，
                // 因此我们F5的时候还是对/user/login进行请求，这样会使表单再次提交一次造成重复提交。
                // 我们在这里进行视图映射后，会变为重定向避免了这个问题。
                registry.addViewController("main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 注册我们编写的登录检查拦截器，并且拦截"/**"项目路径下的所有请求。
                // 但是排除掉登录页面（主页）和提交登录表单的请求。
                // 另外，以前我们使用SpringMVC的时候还要对css、js等静态资源进行排除，但是这里spring boot已经对
                // 静态资源进行了映射，所以我们不需要再进行配置。
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html", "/", "/user/login");
            }
        };
    }

    /**
     * 添加我们自己配置的区域信息解析器组件，从而替换spring boot默认的区域解析器。
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
