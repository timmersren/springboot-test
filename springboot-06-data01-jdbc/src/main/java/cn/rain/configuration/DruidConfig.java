package cn.rain.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * description: Druid数据源的配置类，让配置文件中配置的数据源相关的配置能映射到DruidDataSource中去。
 * 使用@ConfigurationProperties(prefix = "spring.datasource")注解就是为了让配置文件中以"spring.datasource"
 * 开头的配置能映射到我们给容器中添加的DruidDataSource这个组件中去。
 * @author 任伟
 * @date 2018/5/6 13:12
 */
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控,需要配置一个管理后台的Servlet（即StatViewServlet）和一个web监控的filter（即WebStatFilter）。
     * 我们配置这个StatViewServlet的时候，还能给它配置一些属性例如登录的账号密码等，
     * 这些属性都可以在StatViewServlet中及其父类ResourceServlet中找到。
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        // 传入要注册在容器中的Servlet，并且传入要处理哪些URL的请求
        ServletRegistrationBean servletBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "289443");
        // 配置允许谁访问，如果不配或者配置为空，就是允许所有
        initParams.put("allow", "localhost");
        // 配置拒绝谁访问，这里配置为本机的IP
        initParams.put("deny", "192.168.1.4");
        servletBean.setInitParameters(initParams);

        return servletBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new WebStatFilter());
        // 配置filter要拦截的请求,这里拦截所有请求
        filterBean.setUrlPatterns(Arrays.asList("/*"));

        // 配置Filter的初始化参数，要配置的初始化参数都能在WebStatFilter中找到
        Map<String, String> initParams = new HashMap<>();
        // 配置要放行的请求，这里放行静态资源和"/druid/*"下的所有请求
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        filterBean.setInitParameters(initParams);

        return filterBean;
    }
}
