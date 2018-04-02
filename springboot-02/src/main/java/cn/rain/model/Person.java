package cn.rain.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * description: 演示通过@ConfigurationProperties的方式将配置文件中配置的每一个属性的值，映射到这个Person组件中：
 * 1.注解@ConfigurationProperties告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定。
 * 2.prefix = "person"：配置文件中哪个下面的所有属性进行一一映射，@ConfigurationProperties(prefix = "person")
 * 默认从全局配置文件中获取值。
 * 3.只有这个类是容器中的组件，才能使用容器提供的@ConfigurationProperties功能，因此该类需要进行@Component标注。
 * @author 任伟
 * @date 2018/4/1 18:10
 */
@Component
@ConfigurationProperties(prefix = "person")
@Validated //使用@ConfigurationProperties的话支持JSR303数据校验
public class Person {
    private String name;
    @Email
    private String email;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
