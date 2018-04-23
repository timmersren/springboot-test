package cn.rain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * spring boot的主配置类，也可以通过配置类的方式再增加配置类，spring boot - 02 的配置类都在config目录下。
 *
 * spring的原始xml配置文件默认在spring boot中不支持,即便我们编写了，也不会被spring boot加载。
 * 换个角度想，之前我们使用spring的时候也要在web.xml中配置spring配置文件的路径然后才能被加载，
 * 但是在spring boot中根本没有web.xml，因此spring的配置文件肯定不会被加载的。
 * 如果我们想加载自己编写的spring XML配置文件，需要使用注解@ImportResource(locations = {"classpath:beans.xml"})
 * 并且指定要加载的xml文件的位置，且该注解要标注在配置类上（不一定是主配置类，只要是配置类即可，具体见TestService
 * 的测试结果），如spring boot的启动类。如果不使用该注解，无法加载我们自己编写的xml配置文件。
 * 但是spring boot不推荐使用这种方式添加组件，而是推荐使用Java配置类的方式，这里我们将@ImportResources注释掉，
 * 通过cn.rain.config.MyConfig1来添加组件。
 *
 * @author 任伟
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Springboot02Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02Application.class, args);
	}
}
