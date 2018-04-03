package cn.rain.config;

import cn.rain.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: 注解@Configuration指明该类是一个配置类，该类就是用来替代原始的xml配置文件的。
 * 使用注解@Bean给容器中添加组件，@Bean标注的方法的返回值将会被添加到容器中，该组件默认的id是方法名。
 * @author 任伟
 * @date 2018/4/3 10:32
 */
@Configuration
public class MyConfig1 {

    @Bean(name = "helloService1") // 可以使用name属性修改组件的id
    public HelloService helloService(){
        System.out.println("添加helloService组件到容器中");
        return new HelloService();
    }
}
