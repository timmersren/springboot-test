package cn.rain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: 使用@SpringBootApplication注解标注主程序类，说明这是一个spring boot应用。
 * 该注解里面封装了@EnableAutoConfiguration注解，该注解能开启自动装配功能。@EnableAutoConfiguration里面
 * 封装了@AutoConfigurationPackage注解，该注解能自动扫描包，通过查看源码可以知道该注解能扫描到使用了该注解
 * 的类所在的包及其以下的所有包。因此当我们使用了@SpringBootApplication注解后，便能扫描到它所在包及其下面的所有包。
 * @author 任伟
 * @date 2018/4/1 14:26
 */
@SpringBootApplication //它将自动扫描该类所在包（cn.rain）及其所有子包下的类。
public class Application {
    public static void main(String[] args) {
        //在此处运行spring boot应用
        SpringApplication.run(Application.class, args);
    }
}
