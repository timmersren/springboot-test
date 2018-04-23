package cn.rain.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * description: 演示使用@Value(该注解是Spring的底层注解)，为JavaBean注入属性，
 * 该注解类似于spring的xml配置文件<bean>标签下的<properties>子标签，
 * 在@Value里面可以写字面量、${key}从配置文件获取值、#{SpEL}spring表达式。
 * 这里总结下使用@ConfigurationProperties和@Value的区别：
 * 1.@ConfigurationProperties会批量注入配置文件中的属性，而@Value是一个个的注入。
 * 2.@ConfigurationProperties支持松散语法（例如lastName也可写成last-name），而@Value语法严格。
 * 3.@ConfigurationProperties不支持SpEL（spring表达式），@Value支持。
 * 4.@ConfigurationProperties支持JSR303数据校验，@Value不支持。
 * 5.@ConfigurationProperties支持复杂类型的封装（如Person中的Map），而@Value不支持，这也是它俩最大的差别！
 *
 * 总结：如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value。
 * 如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties；
 *
 * @author 任伟
 * @date 2018/4/2 21:43
 */
@Component
public class Person2 {
    @Value("${person.name}") //从配置文件中获取值
    private String name;
    @Value("#{11*2}") //SpEL表达式
    private Integer age;
    @Value("true") // 字面量
    private Boolean boss;
    private Date birth;
//    @Value("${person.map}") 该注解不支持注入复杂类型
    private Map<String, Object> maps;
    private List<Object> lists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                '}';
    }
}
