package cn.rain;

import cn.rain.model.Person;
import cn.rain.model.Person2;
import cn.rain.model.Person3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring boot的单元测试
 * 可以在这里使用@Autowired直接注入容器中的bean
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02ApplicationTests {

	@Autowired
	private Person person;
	@Autowired
	private Person2 person2;
	@Autowired
	private Person3 person3;
	@Autowired
	ApplicationContext context;

	@Test
	public void contextLoads() {
		System.out.println("========================================================");
		System.out.println(person.toString());
	}

	@Test
	public void testValueAnnotation(){
		System.out.println("========================================================");
		System.out.println(person2.toString());
	}

	@Test
	public void testPerson3(){
		System.out.println("========================================================");
		System.out.println(person3.toString());
	}


	@Test
	public void testIoc(){
		// 测试helloService这个bean是否被加载到spring的ioc容器中
		boolean b = context.containsBean("helloService1");
		System.out.println("容器中是否包含helloService1：" + b);
		// 测试@ImportResources是否能标注在任何一个配置类上（不只是主配置类）
		boolean b2 = context.containsBean("testService");
		System.out.println("容器中是否包含testService：" + b2);

//		容器中是否包含helloService1：true
//		容器中是否包含testService：true
		// 测试结果显示@ImportResources只要标注在配置类上就能加载spring的xml配置文件。



	}
}
