package cn.rain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * 1.自动配置类生效后，会自动注入WRainProperties，由于在配置文件中对wrain.hello进行了配置，
 *   因此WRainProperties中的属性就都已经被赋值了。
 * 2.由于HelloService中要依赖WRainProperties给name前后拼串，因此在HelloService依赖了WRainProperties。
 *   然后我们在该自动配置类中通过service.setwRainProperties(wRainProperties)方法将属性已经被赋好值的
 *   WRainProperties传递给HelloService。
 * 3.将HelloService注册到容器中，这样别人就能容器中获取到HelloService这个组件了。只要别人在配置文件中
 *   配置了"wrain.hello"的前后缀，然后调用容器中HelloService的sayHello(String name)方法，就可以看到
 *   "prefix + name + suffix"的效果了，而具体怎么实现的全部被隐藏起来了。
 *
 * @author 任伟
 * @date 2018/5/7 1:37
 */
@Configuration
@ConditionalOnWebApplication // web应用该自动配置类才生效
@EnableConfigurationProperties(WRainProperties.class)
public class WRainAutoConfiguration {

    @Autowired
    private WRainProperties wRainProperties;

    @Bean
    public HelloService helloService(){
        HelloService service = new HelloService();
        service.setwRainProperties(wRainProperties);
        return service;
    }
}
