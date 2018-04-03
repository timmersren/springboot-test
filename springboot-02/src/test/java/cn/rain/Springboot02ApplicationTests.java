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
		System.out.println(b);
	}
}
