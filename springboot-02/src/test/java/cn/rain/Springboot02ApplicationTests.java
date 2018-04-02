package cn.rain;

import cn.rain.model.Person;
import cn.rain.model.Person2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
