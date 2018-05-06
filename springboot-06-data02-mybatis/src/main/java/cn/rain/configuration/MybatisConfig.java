package cn.rain.configuration;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: 定制mybatis的配置，相当于以前mybatis的配置文件。
 * 需要给容器中添加mybatis的定制器组件，该组件是一个接口，我们只需实现其中的方法即可。
 * 定制器接口中的customize(Configuration configuration)方法带有一个参数Configuration，
 * Configuration这个配置类中的属性包含了mybatis中的所有配置，我们都可以通过修改其中的
 * 属性来对mybatis进行定制。
 * @see org.apache.ibatis.session.Configuration
 * @author 任伟
 * @date 2018/5/6 16:09
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                // 开启驼峰命名规则
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
