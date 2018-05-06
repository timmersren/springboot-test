# **一、**Spring Boot 入门

## 1、Spring Boot 简介

> 简化Spring应用开发的一个框架；
>
> 整个Spring技术栈的一个大整合；
>
> J2EE开发的一站式解决方案；

## 2、微服务

2014，martin fowler

微服务：架构风格（服务微化）

一个应用应该是一组小型服务；可以通过HTTP的方式进行互通；

单体应用：ALL IN ONE

微服务：每一个功能元素最终都是一个可独立替换和独立升级的软件单元；

[详细参照微服务文档](https://martinfowler.com/articles/microservices.html#MicroservicesAndSoa)



## 3、环境准备

http://www.gulixueyuan.com/ 谷粒学院

环境约束

–jdk1.8：Spring Boot 推荐jdk1.7及以上；java version "1.8.0_112"

–maven3.x：maven 3.3以上版本；Apache Maven 3.3.9

–IntelliJIDEA2017：IntelliJ IDEA 2017.2.2 x64、STS

–SpringBoot 1.5.9.RELEASE：1.5.9；

统一环境；



### 1、MAVEN设置；

给maven 的settings.xml配置文件的profiles标签添加

```xml
<profile>
  <id>jdk-1.8</id>
  <activation>
    <activeByDefault>true</activeByDefault>
    <jdk>1.8</jdk>
  </activation>
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
  </properties>
</profile>
```

### 2、IDEA设置

整合maven进来；

![idea设置](images/搜狗截图20180129151045.png)



![images/](images/搜狗截图20180129151112.png)

## 4、Spring Boot HelloWorld

一个功能：

浏览器发送hello请求，服务器接受请求并处理，响应Hello World字符串；



### 1、创建一个maven工程（jar）

### 2、导入spring boot相关的依赖

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

### 3、编写一个主程序；启动Spring Boot应用

```java

/**
 *  @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {

        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}
```

### 4、编写相关的Controller、Service

```java
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}

```



### 5、运行主程序测试

### 6、简化部署

```xml
 <!-- 这个插件，可以将应用打包成一个可执行的jar包；-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

然后通过maven的package命令（IDEA）右侧maven栏中运行LifeCycle-->package，将这个应用打成jar包，直接在jar包路径下使用java -jar的命令进行执行；

## 5、Hello World探究

### 1、POM文件

#### 1、父项目

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.9.RELEASE</version>
</parent>

而spring-boot-starter-parent还有父项目：

<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>1.5.9.RELEASE</version>
  <relativePath>../../spring-boot-dependencies</relativePath>
</parent>

spring-boot-dependencies来真正管理Spring Boot应用里面的所有依赖版本；

```

spring-boot-dependencies是Spring Boot的版本仲裁中心；

以后我们导入依赖默认是不需要写版本，（没有在dependencies里面管理的依赖自然需要声明版本号）

#### 2、启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**spring-boot-starter**-【web】：

​	spring-boot-starter-web：是spring-boot的场景启动器，帮我们导入了web模块正常运行所依赖的组件；

![1524377557714](C:\Users\45900\AppData\Local\Temp\1524377557714.png)

Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（如上图），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来。要用什么功能就导入什么场景的启动器。



### 2、主程序类，主入口类

```java
/**
 *  @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {

        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}

```

@**SpringBootApplication**:    Spring Boot应用标注在某个类上说明这个类是SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用；



```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
      @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
      @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```

@**SpringBootConfiguration**:Spring Boot的配置类；

​		标注在某个类上，表示这是一个Spring Boot的配置类；

​		@**Configuration**:配置类上来标注这个注解；

​			配置类 -----  相当于一个xml配置文件；配置类也是容器中的一个组件（@Component



@**EnableAutoConfiguration**：开启自动配置功能；

​		以前我们需要手动配置的东西，现在Spring Boot帮我们自动配置；@**EnableAutoConfiguration**告诉SpringBoot开启自动配置功能；这样自动配置才能生效；

```java
@AutoConfigurationPackage
@Import(EnableAutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
```

​      	@**AutoConfigurationPackage**：自动配置包

​		@**Import**(AutoConfigurationPackages.Registrar.class)：

​		Spring的底层注解@Import，给容器中导入一个组件；导入的组件由AutoConfigurationPackages.Registrar.class指定，因为Registrar这个类实现了ImportBeanDefinitionRegistrar，它会注册一些组件进来。

**通过看源码我们发现，它注册的组件其实就是将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器。**



​	@**Import**(EnableAutoConfigurationImportSelector.class)；

​		给容器中导入组件？

​		**EnableAutoConfigurationImportSelector**：导入哪些组件的选择器；

​		将所有需要导入的组件以全类名的String[]方式返回，这些组件就会被添加到容器中；

​		会给容器中导入非常多的自动配置类（xxxAutoConfiguration），就是给容器中导入这个场景需要的所有组件，并配置好这些组件；		![自动配置类](images/搜狗截图20180129224104.png)

有了自动配置类，免去了我们手动编写配置注入功能组件等的工作。

这些自动配置类使通过调用List<String> configurations = getCandidateConfigurations(annotationMetadata,      attributes)  --> AutoConfigurationImportSelector  95 行。

我们继续深入getCandidateConfigurations（）方法查看，发现最终是通过调用方法SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)  -->  AutoConfigurationImportSelector  151 行。（这里参数列表的参数是通过方法获取到的值）

我们继续深入loadFactoryNames（）方法看它都干了什么：

![1524398558308](C:\Users\45900\AppData\Local\Temp\1524398558308.png)

① 常量 **String FACTORIES_RESOURCE_LOCATION =** **"META-INF/spring.factories";** 通过classLoader到此路径下加载资源，最后得到Enumeration<URL>这样一个迭代集合。

② 循环遍历迭代器中的每一个URL，将每一个URL加载成Properties。

③ 从Properties中通过factoryClassName获取到属性值，其实factoryClassName就是EnableAutoConfiguration指定的值，如下图：

![1524399178843](C:\Users\45900\AppData\Local\Temp\1524399178843.png)

​	

​	总结：我们知道想要启动springboot，需要在主配置类上标注@SpringBootApplication注解，我们通过追该注解的源码一路看到了上面的①②③过程。到这里我们应该知道了，其实标注了该注解，我们追源码到最后发现它会在**springboot启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作。**以前我们需要自己配置的东西，现在自动配置类都帮我们配置好了。



**Spring Boot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作，**以前我们需要自己配置的东西，自动配置类都帮我们；

J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar，如图：

![1524399684234](C:\Users\45900\AppData\Local\Temp\1524399684234.png)



## 6、使用Spring Initializer快速创建Spring Boot项目

### 1、IDEA：使用 Spring Initializer快速创建项目

IDE都支持使用Spring的项目创建向导快速创建一个Spring Boot项目；

选择我们需要的模块；向导会联网创建Spring Boot项目；

默认生成的Spring Boot项目；

- 主程序已经生成好了，我们只需要我们自己的逻辑
- resources文件夹中目录结构
  - static：保存所有的静态资源； js css  images；
  - templates：保存所有的模板页面；（Spring Boot默认jar包使用嵌入式的Tomcat，默认不支持JSP页面）；可以使用模板引擎（freemarker、thymel
  - af）；
  - application.properties：Spring Boot应用的配置文件；可以修改一些默认设置；

### 2、STS使用 Spring Starter Project快速创建项目



-------------



# 二、配置文件

## 1、配置文件

SpringBoot使用一个全局的配置文件，配置文件名是固定的；

•application.properties

•application.yml



配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；



YAML（YAML Ain't Markup Language）

​	YAML  A Markup Language：是一个标记语言

​	YAML   isn't Markup Language：不是一个标记语言；

标记语言：

​	以前的配置文件；大多都使用的是  **xxxx.xml**文件；

​	YAML：**以要配置数据为中心（核心）**，比json、xml等更适合做配置文件（xml大量的数据都浪费在了标签的开闭上）；

​	YAML：配置例子

```yaml
server:
  port: 8081
```

​	XML：

```xml
<server>
	<port>8081</port>
</server>
```



## 2、YAML语法

### 1、基本语法

**同级关系：** key：(空格) value ，表示一对键值对（空格必须有）；

**层级关系：** 以空格的缩进来控制，只要是左对齐的一列数据，都是同一个层级的，如下：

```yaml
server:
    port: 8081
    path: /hello
```

和xml一样属性和值的大小写是敏感的。



### 2、值的类型

#### （1）字面量：普通的值（数字，字符串，布尔）

​	**k：v **  字面量直接来写。

​		字符串默认不用加上单引号或者双引号，如果加上会有特殊意义。

​		**" "双引号**：不会转义字符串里面的特殊字符，特殊字符会作为本身想表示的意思。

​				例如，name:   "zhangsan \n lisi"   输出结果是：zhangsan 换行  lisi

​		**' '单引号**：会转义特殊字符，特殊字符最终只是一个普通的字符串数据

​				name:   ‘zhangsan \n lisi’   输出结果是：zhangsan \n  lisi



#### （2）对象、Map（属性和值）（键值对）：

​	**换行写法：**在下一行来写对象的属性和值的关系，要注意同级属性左对齐，如下：

```yaml
friends:
		lastName: zhangsan
		age: 20
```

​	**行内写法：**key: (空格){属性1的K: (空格)属性1的V，属性2的K: (空格)属性2的V} 

```yaml
friends: {lastName: zhangsan,age: 18}
```



#### （3）数组（List、Set）：

​	**换行写法：**用 **- (空格)V** 表示数组中的一个元素，如下：

```yaml
pets:
 - cat
 - dog
 - pig
```

​	**行内写法**：K: (空格)[V1,V2,V3]

```yaml
pets: [cat,dog,pig]
```



## 3、配置文件值注入

#### 1、属性注入

配置文件

```yaml
person:
    lastName: hello
    age: 18
    boss: false
    birth: 2017/12/12
    maps: {k1: v1,k2: 12}
    lists:
      - lisi
      - zhaoliu
    dog:
      name: 小狗
      age: 12
```

javaBean：

```java
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中:
 * 1.@ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 * 2.prefix属性：由于我们的application.yml配置文件中有很多类别的配置，比如server、person等等，prefix属性就是告诉spring boot要将配置文件中哪个类别的属性和这个组件中的属性进行一一映射。因此我们配置的
prefix = "person"就是告诉spring boot将全局配置文件（默认从全局配置文件中获取值，后边讲怎么修改这个默认值）中person类别的配置映射到这个组件中的属性。
 *
 * 3.只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能，因此该类需要进行@Component标注。
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

```

我们可以导入配置文件处理器，以后编写配置就有提示了

```xml
<!--导入配置文件处理器，配置文件进行绑定就会有提示-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
```

使用spring boot的单元测试框架进行测试：

![1524454844303](C:\Users\45900\AppData\Local\Temp\1524454844303.png)

#### 2、properties配置文件在idea中默认utf-8可能会乱码

调整

![idea配置乱码](images/搜狗截图20180130161620.png)



#### 3、@Value获取值和@ConfigurationProperties获取值比较

|            | @ConfigurationProperties | @Value |
| ---------- | ------------------------ | ------ |
| 功能         | 批量注入配置文件中的属性             | 一个个指定  |
| 松散绑定（松散语法） | 支持                       | 不支持    |
| SpEL       | 不支持                      | 支持     |
| JSR303数据校验 | 支持                       | 不支持    |
| 复杂类型封装     | 支持                       | 不支持    |

配置文件yml还是properties他们都能获取到值；

如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value；

如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties；



#### 4、配置文件注入值数据校验

```java
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
     * <bean/>
     */

   //lastName必须是邮箱格式
    @Email
    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
    //@Value("true")
    private Boolean boss;

    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```



#### 5、@PropertySource&@ImportResource&@Bean

@**PropertySource**：加载指定的配置文件；

```java
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *  @ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；
 *
 */
@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
//@Validated
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
     * <bean/>
     */

   //lastName必须是邮箱格式
   // @Email
    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
    //@Value("true")
    private Boolean boss;

```



@**ImportResource**：导入Spring的配置文件，让配置文件里面的内容生效；

Spring Boot里面默认是没有Spring配置文件的，我们自己编写的配置文件，spring boot也不能自动识别；如果我们想让Spring的配置文件生效，加载进来需要使用@**ImportResource**注解标注在一个配置类上（不非得是主配置类，任何一个配置类都行），这里我们标注在主配置类上，即启动类Springboot02Application上。

```java
@ImportResource(locations = {"classpath:beans.xml"})
导入Spring的配置文件让其生效
```



spring boot不推荐编写Spring的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id="helloService" class="com.atguigu.springboot.service.HelloService"></bean>
</beans>
```

SpringBoot推荐给容器中添加组件的方式：推荐使用全注解的方式

1、配置类**@Configuration**------>相当于Spring配置文件

2、使用**@Bean**给容器中添加组件

```java
/**
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
 *
 * 在配置文件中用<bean><bean/>标签添加组件
 *
 */
@Configuration
public class MyAppConfig {

    //将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService02(){
        System.out.println("配置类@Bean给容器中添加组件了...");
        return new HelloService();
    }
}
```

##4、配置文件占位符

### 1、随机数

```java
${random.value}、${random.int}、${random.long}
${random.int(10)}、${random.int[1024,65536]}

```



### 2、占位符获取之前配置的值，如果没有可以是用:指定默认值

```properties
person.last-name=张三${random.uuid}
person.age=${random.int}
person.birth=2017/12/15
person.boss=false
person.maps.k1=v1
person.maps.k2=14
person.lists=a,b,c
// 获取person.hello的值，没有获取到的话使用冒号后边的默认值hello
person.dog.name=${person.hello:hello}_dog 
person.dog.age=15
```



## 5、Profile

### 1、多Profile文件

我们在主配置文件编写的时候，文件名可以是   application-{profile}.properties	/yml，如图：

![1524463668046](C:\Users\45900\AppData\Local\Temp\1524463668046.png)

但是spring boot默认使用application.properties的配置，如何切换到其他环境的配置文件看下面的3、激活指定profile方式中的讲解。



### 2、yml支持多文档块方式

```yml

# yml文件以---为分割，每一个---中都是一个文档块（相当于一个配置文件）

# 可以通过spring:(换行缩进) profiles: xxx，来指定该文档块的环境名，如果不写的话默认就是主文档块，
# spring boot默认会加载主文档块，然后根据主文档块中设置要激活的环境来激活某个环境的文档块。

server:
  port: 8081

#激活profile为prod的文档块
spring:
  profiles:
    active: prod  

---
server:
  port: 8083
spring:
  profiles: dev	 #指定该文档块属于dev环境


---

server:
  port: 8084
spring:
  profiles: prod  #指定该文档块属于prod环境
```





### 3、激活指定profile的方式

#### 	（1）在全局配置文件中指定

​		在默认加载的全局配置文件中指定  spring.profiles.active=dev，如图：

![1524464074541](C:\Users\45900\AppData\Local\Temp\1524464074541.png)

#### 	（2）命令行

​		打包之后，我们可以在运行jar包的时候通过参数来指定要运行的环境，命令如下：

​		java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

​		如图：

![1524465202479](C:\Users\45900\AppData\Local\Temp\1524465202479.png)



​		如果我们还没到打包部署阶段，而只是使用IDEA进行测试，那么可以在启动主配置类之前配置命令行参数，如图：

![1524465349772](C:\Users\45900\AppData\Local\Temp\1524465349772.png)



#### 	（3）虚拟机参数

​		-Dspring.profiles.active=prod

![1524465699552](C:\Users\45900\AppData\Local\Temp\1524465699552.png)



## 6、配置文件加载位置

#### 1.SpringBoot启动时默认加载四个路径的配置文件

springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件

–file:./config/

–file:./

–classpath:/config/

–classpath:/

上面从上到下的四个路径的优先级由高到底。SpringBoot启动的时候会从这四个位置全部加载主配置文件高优先级的配置会覆盖低优先级的配置，但是这种覆盖并非文件级别的覆盖，而是配置级的，也就说优先级高和低的两个配置文件中都有同一个配置时，高优先级会覆盖低优先级，如果高优先级中没有A配置，而低优先级中有，那么springboot启动后也会加载到该配置，这称为**互补配置**。



#### 2.更改默认加载的配置文件路径

我们还可以通过spring.config.location来改变默认的配置文件位置，配置方法如下：

**项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；指定配置文件和默认加载的这些配置文件共同起作用形成互补配置；**

java -jar springboot-02-config2-0.0.1-SNAPSHOT.jar --spring.config.location=F:/application.properties

如图：

![1524468521152](C:\Users\45900\AppData\Local\Temp\1524468521152.png)

![1524468487139](C:\Users\45900\AppData\Local\Temp\1524468487139.png)

​	当我们在打包好项目并且准备部署的时候，突然发现有一些配置还要写入配置文件，通过此特性我们便可以加载外部配置文件中的配置，并且和项目中配置形成互补。省去了我们修改项目中的配置文件再从新打包部署的时间。





## 7、外部配置加载顺序

​	**SpringBoot除了可以从配置文件中加载配置，也可以从以下11个位置（官方文档给出17个，这里做了精简）加载配置。它们的优先级是依次从高到低的，高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置：**



**1.命令行参数**

​	所有的配置都可以在命令行上进行指定，且该中配置形式的优先级最高，通过命令行进行的配置将覆盖其它位置的此种配置：

​	java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --server.port=8087  --server.context-path=/abc

​	**命令行参数配置格式为：java -jar xxx.jar --配置项1=值1 配置项2=值2，且多个配置用空格分开。**



2.来自java:comp/env的JNDI属性

3.Java系统属性（System.getProperties()）

4.操作系统环境变量

5.RandomValuePropertySource配置的random.*属性值



**由jar包外向jar包内进行寻找**

**优先加载带profile**

**6.jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件**

**7.jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件**



**再来加载不带profile**

**8.jar包外部的application.properties或application.yml(不带spring.profile)配置文件**

**9.jar包内部的application.properties或application.yml(不带spring.profile)配置文件**

注：6和9中所说的jar包外边指的是和jar包在同一个目录下，这样springboot启动时会自动扫描，不用我们自己指定spring.config.location，如图：

![52273969801](C:\Users\45900\AppData\Local\Temp\1522739698014.png)





10.@Configuration注解类上的@PropertySource

11.通过SpringApplication.setDefaultProperties指定的默认属性

**这里的11种只是常用的，总共17中，所有支持的配置加载来源**[参考官方文档](https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/#boot-features-external-config)



## 8、自动配置原理

application.properties/application.yml配置文件到底能写什么？怎么写？这一节将讲解自动配置的原理。



配置文件中具体能配什么可以参照官方文档：[配置文件能配置的属性参照](https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/#common-application-properties)



### 1、**自动配置原理：**

1）、SpringBoot启动的时候加载主配置类，开启了自动配置功能 **@EnableAutoConfiguration**

**2）、@EnableAutoConfiguration 作用：**

 - 利用EnableAutoConfigurationImportSelector给容器中导入一些组件。

- 可以查看父类AutoConfigurationImportSelector的selectImports()方法的内容。

- List<String> configurations = getCandidateConfigurations(annotationMetadata,      attributes);获取候选的配置：

  - ```java
    SpringFactoriesLoader.loadFactoryNames()
    扫描所有jar包类路径下  META-INF/spring.factories
    把扫描到的这些文件的内容包装成properties对象
    从properties中获取到key=EnableAutoConfiguration.class类（全类名）对应的值，然后把他们添加在容器中

    ```

    ​		

**将类路径下  META-INF/spring.factories 里面配置的所有EnableAutoConfiguration的值加入到了容器中，EnableAutoConfiguration.class的全类名是org.springframework.boot.autoconfigure.EnableAutoConfiguration，因此从Properties对象中获取属性时使用的key就应该是这个全类名，因此如下图，这个key只对应着一个value，但是这个value里有很多的组件（全类名），它们通过逗号分隔：**

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration,\
org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
org.springframework.boot.autoconfigure.mobile.DeviceResolverAutoConfiguration,\
org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverAutoConfiguration,\
org.springframework.boot.autoconfigure.mobile.SitePreferenceAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
org.springframework.boot.autoconfigure.reactor.ReactorAutoConfiguration,\
org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration,\
org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration,\
org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration,\
org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration,\
org.springframework.boot.autoconfigure.social.LinkedInAutoConfiguration,\
org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration,\
org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration,\
org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration,\
org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration,\
org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration,\
org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration,\
org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration
```

**每一个这样的xxxAutoConfiguration类都是容器中的一个组件，最终都加入到容器中，用他们来做自动配置。**

3）、每一个自动配置类进行自动配置功能。

4）、以**HttpEncodingAutoConfiguration（Http编码自动配置）**为例解释自动配置原理；

```java
@Configuration   //表示这是一个配置类，类似以前编写的xml配置文件一样，可以通过@Bean给容器中添加组件
@EnableConfigurationProperties(HttpEncodingProperties.class)  //启动value属性中指定类的ConfigurationProperties功能（即标注了@ConfigurationProperties注解的类），将application.properties配置文件中配置的值和HttpEncodingProperties类中的属性绑定起来，并把HttpEncodingProperties加入到ioc容器中

@ConditionalOnWebApplication //Spring底层@Conditional注解（Spring注解版），根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效（因为不满足条件该配置类就不会被加入到容器中）。判断当前应用是否是web应用，如果是，当前配置类生效。

@ConditionalOnClass(CharacterEncodingFilter.class)  //（扫描）判断当前项目有没有这个class文件，有这个class当前配置才生效。CharacterEncodingFilter是SpringMVC中进行乱码解决的过滤器。

@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)  //判断application.properties配置文件中是否存在这个配置：spring.http.encoding.enabled，如果不存在，@ConditionalOnProperty判断也是成立的，即当前配置也会生效。matchIfMissing这个属性值默认是false。
//即使我们配置文件中不配置pring.http.encoding.enabled=true，也是默认生效的；
public class HttpEncodingAutoConfiguration {
    
    //一旦上边的判断全都通过了，就会执行类中的方法给容器添加组件。
  
  	//他已经和SpringBoot的application.properties配置文件映射了
  	private final HttpEncodingProperties properties;
  
   	//该类只有一个有参构造器的情况下，参数的值就会从容器中拿，由于上边已经通过@EnableConfigurationProperties(HttpEncodingProperties.class)注解将HttpEncodingProperties加入到ioc容器中了，因此这里可从容器中直接拿到。
  	public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
		this.properties = properties;
	}
  
    @Bean   //给容器中添加一个组件，这个组件的某些值需要从properties中获取
	@ConditionalOnMissingBean(CharacterEncodingFilter.class) //判断容器是不是没有这个组件？没有的话判断成立
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding(this.properties.getCharset().name());
		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
		return filter;
	}
```

根据当前不同的条件判断，决定这个配置类是否生效？

一但这个配置类生效，这个配置类就会给容器中添加各种组件，这些组件的属性是从对应的properties（如本例的HttpEncodingProperties）类中获取的，这些类里面的每一个属性又是和application.properties配置文件绑定的。



5）、所有在配置文件中能配置的属性都是在xxxxProperties类中封装着，配置文件能配置什么就可以参照某个功能对应的这个属性类

```java
@ConfigurationProperties(prefix = "spring.http.encoding")  //从application.properties配置文件中获取我们配置的值和该类的属性进行绑定
public class HttpEncodingProperties {

   public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
```

如图：

![1524580228167](C:\Users\45900\AppData\Local\Temp\1524580228167.png)



**精髓：**

​	**1）、SpringBoot启动会加载大量的自动配置类**

​	**2）、我们看我们需要的功能有没有SpringBoot默认写好的自动配置类。**

​	**3）、我们再来看这个自动配置类中到底配置了哪些组件（只要我们要用的组件有，我们就不需要再来配置了）。**

​	**4）、自动配置类给容器中添加组件的时候，会从properties类中获取某些属性，这些属性是和application.properties配置文件绑定的，因此我们就可以在配置文件中指定这些属性的值。**



xxxxAutoConfigurartion：自动配置类，给容器中添加组件。

xxxxProperties:封装配置文件中相关属性。



### 2、细节



#### 1、@Conditional派生注解（参看Spring注解版原生的@Conditional作用）

​	**作用：**

- @Conditional标注在类上，条件成立该配置类才会被加入到容器中，配置类中的方法（即通过@Bean给容器添加组件）才有可能被执行（因为还要看方法上的@Conditional）。
- @Conditional标注在方法上，前提是类上的@Conditional判断成功，使得配置类加入到容器中，该方法才会被spring扫描到。然后判断该方法上的@Conditional，判断成功会给容器添加组件，否则不添加。

| @Conditional扩展注解                | 作用（判断是否满足当前指定条件）               |
| ------------------------------- | ------------------------------ |
| @ConditionalOnJava              | 系统的java版本是否符合要求                |
| @ConditionalOnBean              | 容器中存在指定Bean；                   |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean；                  |
| @ConditionalOnExpression        | 满足SpEL表达式指定                    |
| @ConditionalOnClass             | 系统中有指定的类                       |
| @ConditionalOnMissingClass      | 系统中没有指定的类                      |
| @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                 |
| @ConditionalOnWebApplication    | 当前是web环境                       |
| @ConditionalOnNotWebApplication | 当前不是web环境                      |
| @ConditionalOnJndi              | JNDI存在指定项                      |

**自动配置类必须在一定的条件下才能生效：**

虽然spring.factories中配置了那么多的xxxAutoConfiguration，但是每个自动配置类都是需要很多的@Conditional判断成立后才能被加入到容器中的，例如：

![](D:\搜狗输入法\搜狗截图\搜狗截图20180426134952.png)

由于我们没有导入aop相关的jar包，因此@ConditionalOnClass判断不成立，故这个自动配置类就不会加入到容器中。

**springboot启动时这么多的自动配置类需要进行加载，我们怎么知道最终哪些自动配置类判断成立并且生效了呢？**

我们可以通过在application.properties配置文件中配置**debug=true**属性；来让控制台打印自动配置类的报告，这样我们就可以很方便的知道哪些自动配置类生效，如图：

![](D:\搜狗输入法\搜狗截图\搜狗截图20180426135557.png)



![](D:\搜狗输入法\搜狗截图\搜狗截图20180426135833.png)

```java
=========================
AUTO-CONFIGURATION REPORT
=========================


Positive matches:（自动配置类启用的，即匹配成功的）
-----------------

   DispatcherServletAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
      - @ConditionalOnWebApplication (required) found StandardServletEnvironment (OnWebApplicationCondition)
        
    
Negative matches:（没有启用的，即没有匹配成功的自动配置类）
-----------------

   ActiveMQAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required classes 'javax.jms.ConnectionFactory', 'org.apache.activemq.ActiveMQConnectionFactory' (OnClassCondition)

   AopAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required classes 'org.aspectj.lang.annotation.Aspect', 'org.aspectj.lang.reflect.Advice' (OnClassCondition)
        
```





# 三、日志

## 1、日志框架

 小张开发一个大型系统；

​	1、为了便于调试故将关键数据通过System.out.println("")打印在控制台，但是开发完成后这些数据是去掉？还是写在一个文件？

​	2、于是小张开发了一个框架来记录系统的一些运行时信息。日志框架--zhanglogging.jar。

​	3、后续想对该框架开发几个高大上的功能，异步模式、自动归档、等等 。命名为--zhanglogging-good.jar？

​	4、如果将以前框架zhanglogging卸下来，换上新的框架zhanglogging-good，需要重新修改之前相关的API，这一版命名为--zhanglogging-prefect.jar。

​	5、小张想了前四步觉得每次有新的框架就要把之前嵌入的框架全部替换掉很麻烦，某天他突然想到了web开发中的JDBC的设计模式--数据库驱动：

​		写了一个统一的接口层，日志门面（日志的一个抽象层），logging-abstract.jar。

​		给项目中导入具体的日志实现就行了，我们之前的日志框架都是实现的抽象层。



**市面上的日志框架：**

JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j....

| 日志门面  （日志的抽象层）                                   | 日志实现                                                     |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| ~~JCL（Jakarta  Commons Logging）~~    SLF4j（Simple  Logging Facade for Java）    **~~jboss-logging~~** | Log4j  ~~JUL（java.util.logging）~~  ~~Log4j2~~  **Logback** |

左边选一个门面（抽象层）、右边来选一个实现；

**日志门面**：  **SLF4J**

~~jboss-logging~~：使用场景很少，一般不使用。

~~JCL~~：最后更新在2014年，过时了。



**日志实现**：**Logback**

~~JUL~~: 在log4j出现以后java担心日志市场被占领才勉强写出来的，性能不好，不使用。

~~Log4j2~~：是Apache借着Log4j的名气对其进行的升级，性能很强，但是由于过于超前，目前适配性不好。



**Log4j、Logback、slf4j都是出自同一个作者。他最先写了log4j，后边感觉到性能不好决定更改，但是改动实在太大了，因此他又重新写了一个项目Logback，它也意识到了小张面临的问题，每次替换框架都很麻烦于是他又写了框架的抽象slf4j。**



**Spring**：底层使用的**JCL**，所以我们之前使用spring的时候要求必须导入commons-logging就是因为这个原因。

**SpringBoot：通过对spring的再包装最终选用 SLF4j和logback。**



## 2、SLF4j使用

### 1、如何在系统中使用SLF4j   https://www.slf4j.org

以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日志抽象层里面的方法。

给系统里面导入slf4j的jar包和logback的实现jar包，通过调用抽象层slf4j的方法完成日志记录，如下：

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```



图示：

![images/concrete-bindings.png](images/concrete-bindings.png)

每一个日志的实现框架都有自己的配置文件。使用slf4j以后，**配置文件还是做成日志实现框架自己本身的配置文件，slf4j仅仅是提供了日志抽象层的功能，它没有配置文件。**



### 2、遗留问题

A系统（slf4j+logback）: Spring（commons-logging）、Hibernate（jboss-logging）、MyBatis、xxxx。

我们想统一日志框架，希望该系统集成的框架即使是别的框架，最终也一起统一使用slf4j进行输出，应该怎么做？

下图中左上这幅图讲解了具体怎么做，其他3幅图都是具体举例：

![](images/legacy.png)

**如何让系统中所有的日志都统一到slf4j：**

1、将系统中其他日志框架先排除出去，如上图左边所示的Commons logging-API，假设系统中集成了spring，这样操作后spring会由于缺包而无法启动。

2、用中间包来替换原有的日志框架，即去掉commons-logging.jar引入jcl-over-slf4j.jar。引入的这个替换包含有之前commons-logging中的所有类和方法，这样spring就可以启动了，而jcl-over-slf4j.jar最大的作用是将所有的方法调用都指向slf4j，相当于一个偷天换日的过程。

3、导入我们想使用的slf4j的具体实现，如果需要加适配包的话别忘了加适配（除了Logback大部分都需要适配包）。



## 3、SpringBoot日志关系

SpringBoot使用spring-boot-starter-logging这个启动器来做日志功能：

```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-logging</artifactId>
	</dependency>
```

底层依赖关系

![](images/搜狗截图20180131220946.png)

总结：

**1）、SpringBoot底层是使用slf4j+logback的方式进行日志记录。**

**2）、SpringBoot是通过中间替换包把其他的日志都替换成了slf4j来实现的统一使用slf4j抽象框架。**



如下图所示，jcl-over-slf4j.jar下面的目录结构和commons-logging一模一样，但是在具体的实现中new的是slf4j的对象：

![](D:\搜狗输入法\搜狗截图\搜狗截图20180427134128.png)

```java
@SuppressWarnings("rawtypes")
public abstract class LogFactory {

    static String UNSUPPORTED_OPERATION_IN_JCL_OVER_SLF4J = "http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_over_slf4j";

    static LogFactory logFactory = new SLF4JLogFactory(); // 实现中指向slf4j
```

![](images/搜狗截图20180131221411.png)



​	**3）、如果我们要引入其他框架，这个框架中有默认的日志实现，那么我们一定要把这个框架的默认日志依赖（日志门面抽象框架）移除掉**，我们看通过springboot的配置来看一下他引入spring的时候怎么排除掉spring的默认日志commons-logging的，如下：

```xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<exclusions>
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```

**SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候，只需要把这个框架依赖的日志（抽象门面）框架排除掉即可。**



## 4、日志使用

### 1、默认配置

SpringBoot默认帮我们配置好了日志；

```java
	//记录器
	Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void contextLoads() {
		//System.out.println();

		//日志的级别；
		//由低到高   trace<debug<info<warn<error
		//可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
		logger.trace("这是trace日志...");
		logger.debug("这是debug日志...");
		//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
		logger.info("这是info日志...");
		logger.warn("这是warn日志...");
		logger.error("这是error日志...");


	}
```



        日志输出格式：
    		%d表示日期时间，
    		%thread表示线程名，
    		%-5level：级别从左显示5个字符宽度
    		%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
    		%msg：日志消息，
    		%n是换行符
        
        格式：%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n
        输出：2018-04-27 [main] ERROR cn.rain.Springboot03LoggingApplicationTests - 这是error日志...

SpringBoot修改日志的默认配置举例：

```properties
logging.level.com.atguigu=trace


#logging.path=
# 不指定路径在当前项目下生成springboot.log日志
# 可以指定完整的路径；
#logging.file=G:/springboot.log

# 在当前磁盘的根路径下创建spring文件夹和里面的log文件夹；使用 spring.log 作为默认文件
logging.path=/spring/log

#  在控制台输出的日志的格式
logging.pattern.console=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n
# 指定文件中日志输出的格式
logging.pattern.file=%d{yyyy-MM-dd} === [%thread] === %-5level === %logger{50} ==== %msg%n
```

| logging.file | logging.path | Example  | Description                                          |
| ------------ | ------------ | -------- | ---------------------------------------------------- |
| (none)       | (none)       |          | 只在控制台输出                                       |
| 指定文件名   | (none)       | my.log   | 输出日志到指定路径及文件名的xxx文件                  |
| (none)       | 指定目录     | /var/log | 输出到指定目录的 spring.log 文件中（使用默认文件名） |

### 2、指定配置

springboot已经为我们做了关于日志的默认配置，如果我们不想使用它的默认配置，而是想用自己的日志配置文件，只要给类路径下放上每个日志框架自己的配置文件即可，SpringBoot就不使用他默认配置的了，命名规则如下：

| Logging System          | Customization                            |
| ----------------------- | ---------------------------------------- |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`      |
| JDK (Java Util Logging) | `logging.properties`                     |

当我们使用logback的时候，我们看到可以有两种命名方式**"logback.xml(groovy)"、"logback-spring.xml(groovy)"**，但是springboot官方推荐我们使用带spring扩展名的命名方式，这是因为如果命名成logback.xml那么logback框架会直接识别这个文件名，从而直接被logback框架加载我们的配置；如果我们使用的是"logback-spring.xml"那么logback不会识别这个文件名，但是却可以被spring boot识别，那么将会由springboot为我们加载配置，这样我们可以使用springboot对于日志配置的一个高级功能--日志profile，标签如下：

```xml
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
  	可以指定某段配置只在某个环境下生效
</springProfile>

```

举例如下：

```xml
<appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
			%d表示日期时间，
			%thread表示线程名，
			%-5level：级别从左显示5个字符宽度
			%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
			%msg：日志消息，
			%n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <springProfile name="dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ----> [%thread] ---> %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
            <springProfile name="!dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
        </layout>
    </appender>
```

**注：如果使用logback.xml作为日志配置文件名，就不能使用<springProfile>了，如果还使用的话，会有以下错误：**

 `no applicable action for [springProfile]`

如图：

![报错](D:\搜狗输入法\搜狗截图\QQ拼音截图20180429191848.png)

## 5、切换日志框架

可以按照slf4j的日志适配图，进行相关的切换；

slf4j+log4j的方式；

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <!-- 先将logback实现排除 -->  
    <exclusion>
      <artifactId>logback-classic</artifactId>
      <groupId>ch.qos.logback</groupId>
    </exclusion>
    <exclusion>
      <artifactId>log4j-over-slf4j</artifactId>
      <groupId>org.slf4j</groupId>
    </exclusion>
  </exclusions>
</dependency>

 <!-- 由于项目中都是面向slf4j编程，故如果我们想使用logg4j需要导入slf4j-logg4j包，
 	  这样才能是slf4j的api指向logg4j-->  
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
</dependency>

```



切换为log4j2，需要排除掉spring boot默认使用的starter-logging，切换为starter-log4j2。更换为starter-log4j2这个启动器，它也是和之前starter-logging中做的事情一样，帮我们将其他的日志框架转为slf4j，然后加入具体的实现，只不过starter-log4j2这个启动器实现的是log4j2，如图：

![更换为log4j2](D:\搜狗输入法\搜狗截图\QQ拼音截图20180429195143.png)

具体pom配置如下：

```xml
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
       		<!-- 排除之前的日志starter -->
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>

<!-- 引入log4j2的starter -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

-----------------

# 四、Web开发

## 1、简介



使用SpringBoot，如果使用默认的配置，只需以下三部即可：

**1）、创建SpringBoot应用，选中我们需要的模块。**

**2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来。**

**3）、自己编写业务代码。**



**自动配置原理？**

这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？等等

```
xxxxAutoConfiguration：自动配置类会帮我们给容器中自动配置组件。
而自动配置类上会通过xxxxProperties注解来封装配置文件的内容，需要我们在全局配置文件中进行配置。

```



## 2、SpringBoot对静态资源的映射规则

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties implements ResourceLoaderAware {
//可以设置和静态资源有关的参数，比如缓存时间等
```

我们在WebMvcAutoConfiguration.java这个自动配置类中，注意下面这个方法：

```java
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			if (!this.resourceProperties.isAddMappings()) {
				logger.debug("Default resource handling disabled");
				return;
			}
			Integer cachePeriod = this.resourceProperties.getCachePeriod();
			if (!registry.hasMappingForPattern("/webjars/**")) {
				customizeResourceHandlerRegistration(
						registry.addResourceHandler("/webjars/**")
								.addResourceLocations(
										"classpath:/META-INF/resources/webjars/")
						.setCachePeriod(cachePeriod));
			}
			String staticPathPattern = this.mvcProperties.getStaticPathPattern();
          	//静态资源文件夹映射
			if (!registry.hasMappingForPattern(staticPathPattern)) {
				customizeResourceHandlerRegistration(
						registry.addResourceHandler(staticPathPattern)
								.addResourceLocations(
										this.resourceProperties.getStaticLocations())
						.setCachePeriod(cachePeriod));
			}
		}

        //配置欢迎页映射
		@Bean
		public WelcomePageHandlerMapping welcomePageHandlerMapping(
				ResourceProperties resourceProperties) {
			return new WelcomePageHandlerMapping(resourceProperties.getWelcomePage(),
					this.mvcProperties.getStaticPathPattern());
		}

       //配置喜欢的图标
		@Configuration
		@ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
		public static class FaviconConfiguration {

			private final ResourceProperties resourceProperties;

			public FaviconConfiguration(ResourceProperties resourceProperties) {
				this.resourceProperties = resourceProperties;
			}

			@Bean
			public SimpleUrlHandlerMapping faviconHandlerMapping() {
				SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
				mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
              	//所有  **/favicon.ico 
				mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
						faviconRequestHandler()));
				return mapping;
			}

			@Bean
			public ResourceHttpRequestHandler faviconRequestHandler() {
				ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
				requestHandler
						.setLocations(this.resourceProperties.getFaviconLocations());
				return requestHandler;
			}

		}

```



**1）、所有 /webjars/ \**路径的访问，都会映射到classpath:/META-INF/resources/webjars/ 路径下去找资源。**

​	**webjars：以jar包的方式引入静态资源，之前我们都是将jquery等放入webapp下，但是现在springboot没有webapp，因此我们可以通过jar包（maven依赖）的形式将它们导入进来，具体maven的依赖配置，我们可以到下面这个网站去查找：http://www.webjars.org/，如图：**

![](D:\搜狗输入法\搜狗截图\QQ拼音截图20180430193343.png)



![](D:\搜狗输入法\搜狗截图\QQ拼音截图20180430193536.png)



![](images/搜狗截图20180203181751.png)



访问  localhost:8080/webjars/jquery/3.3.1/jquery.js 	如图：

![](D:\搜狗输入法\搜狗截图\QQ拼音截图20180430194252.png)





**2）、"/\**" 访问当前项目的任何资源，都去（静态资源的文件夹）找映射**

```
"classpath:/META-INF/resources/", 
"classpath:/resources/",
"classpath:/static/", 
"classpath:/public/" 
"/"：当前项目的根路径
```

如图：

![1525089111002](D:\搜狗输入法\搜狗截图\1525089111002.png)



加入我们访问localhost:8080/abc，如果这个请求没有controller进行处理，那么它回去默认的静态资源文件夹里面（上面说的那几个路径）去找abc，我们来访问http://localhost:8080/asserts/js/Chart.min.js，如图：

![](D:\搜狗输入法\搜狗截图\QQ拼音截图20180430195858.png)



**3）、欢迎页：静态资源文件夹下的所有index.html页面；被"/\**"映射。**

​	例如我们访问 localhost:8080/   ，将会去找index页面。

**4）、所有的 \**/favicon.ico （图标）都是默认在静态资源文件下找。**



当然，我们也可以修改静态资源文件的默认加载路径，修改全局配置文件即可，如图：

![](D:\搜狗输入法\搜狗截图\QQ拼音截图20180430201418.png)





## 3、模板引擎

常见的模板引擎有：JSP、Velocity、Freemarker、Thymeleaf等等。

![](images/template-engine.png)



**所有模板引擎的核心思想都是在静态页面上通过一定的语法，然后将数据填充到静态页面上，这个工作由模板引擎来做，例如jsp，然后将填充好的页面返回。**

SpringBoot推荐的模板引擎是Thymeleaf，因为它语法更简单，功能更强大。



### 1、引入thymeleaf；

```xml
		<!-- 引入thymeleaf的starter，默认使用的版本是2.1.6 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>

<properties>
    	<!-- 
		由于2.1.6版本过低，因此我们需要切换为更高的版本，
		在POM的properties标签中做如下配置即可切换为3.0.9版本
 		-->
		<thymeleaf.version>3.0.9.RELEASE</thymeleaf.version>
		<!-- 布局功能的支持程序  thymeleaf3主程序  layout2以上版本 -->
		<!--如果是thymeleaf2，应该适配layout1及以上版本-->
		<thymeleaf-layout-dialect.version>2.2.2</thymeleaf-layout-dialect.version>
  </properties>
```



### 2、Thymeleaf使用

我们通过ThymeleafProperties看一下thymeleaf的默认规则：

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

	private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

    // 默认路径是classpath下的templates
	public static final String DEFAULT_PREFIX = "classpath:/templates/";
	// 默认文件的后缀是.html
	public static final String DEFAULT_SUFFIX = ".html";
  	
```

只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染，并且会为我们的return路径自动加上.html后缀，如图，由于我们没有使用@ResponseBody注解，因此springMVC不会以json串为我们返回结果，而是返回一个视图，而视图解析器就是thymeleaf，它会到classpath:/templates/ 路径下去寻找我们要返回的视图（并且会默认加上.html后缀）：

![](D:\搜狗输入法\搜狗截图\thymeleaf默认规则.png)



![](D:\搜狗输入法\搜狗截图\thymeleaf访问.png)

**使用Thymeleaf：**

1、在html页面导入thymeleaf的名称空间，导入后会有语法提示：

```xml
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

2、使用thymeleaf语法；

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>成功！</h1>
    <!--
	th:text 将div里面的文本内容设置为我们指定的值，这里我们通过${hello}指定为
	域属性中map的key即“你好”，如果我们不使用模板引擎，页面中会显示<div>标签中
	的“这是显示欢迎信息”，使用模板引擎后会替换为后端传来的值。
	-->
    <div th:text="${hello}">这是显示欢迎信息</div>
</body>
</html>
```

### 3、语法规则

1）、th:text	改变当前元素里面的文本内容，正如上面的例子一样，标签中的文本内容被替换成了域中的值。

​	  而且不仅可以替换文本内容的源生值，其他的属性值也可以进行替换，例如使用th: id替换原生的id属性等等，如图我们通过查看页面源码看到id的属性值是“你好”，其实我们的静态页面中id的原生值的div1：

![](D:\搜狗输入法\搜狗截图\th替换原生属性.png)



在Thymeleaf的官方文档的第10章 Attribute precedence 中有对属性的优先级进行详细的说明，如图：  

![](images/2018-02-04_123955.png)



2）、表达式

```properties
Simple expressions:（表达式语法）
    Variable Expressions ${...}:获取变量值，底层通过OGNL实现。
    		1）、获取对象的属性、调用方法
    		2）、使用内置的基本对象：
    			#ctx : the context object.
    			#vars: the context variables.
                 #locale : the context locale.
                 #request : (only in Web Contexts) the HttpServletRequest object.
                 #response : (only in Web Contexts) the HttpServletResponse object.
                 #session : (only in Web Contexts) the HttpSession object.
                 #servletContext : (only in Web Contexts) the ServletContext object.   
                 具体使用方法参考官方文档 18章 Appendix A
                 
            3）、内置的一些工具对象：
                #execInfo : information about the template being processed.
                #messages : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
                #uris : methods for escaping parts of URLs/URIs
                #conversions : methods for executing the configured conversion service (if any).
                #dates : methods for java.util.Date objects: formatting, component extraction, etc.
                #calendars : analogous to #dates , but for java.util.Calendar objects.
                #numbers : methods for formatting numeric objects.
                #strings : methods for String objects: contains, startsWith, prepending/appending, etc.
                #objects : methods for objects in general.
                #bools : methods for boolean evaluation.
                #arrays : methods for arrays.
                #lists : methods for lists.
                #sets : methods for sets.
                #maps : methods for maps.
                #aggregates : methods for creating aggregates on arrays or collections.
                #ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
                具体使用方法参考官方文档 18章 Appendix B

    Selection Variable Expressions *{...} : 选择表达式：和${}在功能上是一样，官方文档4.3。
    	补充功能（文档21页），配合 th：object进行使用，例如：
           <div th:object="${session.user}">
               <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
               <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
               <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
           </div>
    
    Message Expressions #{...}: 获取国际化内容
    
    Link URL Expressions @{...} : 定义URL超链接（详细举例见文档21页）。
    		@{/order/process(execId=${execId},execType='FAST')}
   
    Fragment Expressions ~{...} : 片段引用表达式
    		<div th:insert="~{commons :: main}">...</div>
    		
    		
Literals（字面量）
      Text literals: 'one text' , 'Another one!' ,…
      Number literals: 0 , 34 , 3.0 , 12.3 ,…
      Boolean literals: true , false
      Null literal: null
      Literal tokens: one , sometext , main ,…
Text operations:（文本操作）
    String concatenation: +
    Literal substitutions: |The name is ${name}|
Arithmetic operations:（数学运算）
    Binary operators: + , - , * , / , %
    Minus sign (unary operator): -
Boolean operations:（布尔运算）
    Binary operators: and , or
    Boolean negation (unary operator): ! , not
Comparisons and equality:（比较运算）
    Comparators: > , < , >= , <= ( gt , lt , ge , le )
    Equality operators: == , != ( eq , ne )
Conditional operators:条件运算（三元运算符）
    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
Special tokens: 特殊操作
    No-Operation（不做任何事情）: _ 
```

## 4、SpringMVC自动配置

[使用方法（官方文档）](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### 1. Spring MVC auto-configuration

Spring Boot 自动配置好了SpringMVC

以下是SpringBoot对SpringMVC的默认配置（复制自官方文档）:**（WebMvcAutoConfiguration）**

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.
  - 自动配置了ViewResolver（视图解析器：根据方法的返回值得到视图对象（View），视图对象决定如何渲染（转发或是重定向）
  - ContentNegotiatingViewResolver：组合所有的视图解析器的。
  - **定制视图解析器：我们可以自己给容器中添加一个视图解析器，ContentNegotiatingViewResolver会自动的将其组合进来。**

- Support for serving static resources, including support for WebJars (see below)：静态资源文件夹路径,webjars。

- Static `index.html` support.：静态首页访问

- Custom `Favicon` support (see below)：图标，例如 favicon.ico

- 自动注册了 of `Converter`, `GenericConverter`, `Formatter` beans.

  - Converter 转换器：例如 public String hello(User user)，将请求中的字符串18转成Integer类型的18，使用转换器Converter。
  - `Formatter`  格式化器：例如将页面请求的2017.12.17转成Date类型。

```java
		@Bean
		//在文件中配置日期格式化的规则，由于@ConditionalOnProperty判断条件，因此如果配了容器中就会加			入格式化组件，否则不加。
		@ConditionalOnProperty(prefix = "spring.mvc", name = "date-format")
		public Formatter<Date> dateFormatter() {
             //容器中加入日期格式化组件
			return new DateFormatter(this.mvcProperties.getDateFormat());
		}
```

​	**自己添加的格式化器转换器，我们只需要放在容器中即可**

- Support for `HttpMessageConverters` (see below).

  - HttpMessageConverter：SpringMVC用来转换Http请求和响应的。例如想把User以Json形式响应，就要使用HttpMessageConverter进行转换。

  - `HttpMessageConverters` 是从容器中确定，获取所有的HttpMessageConverter。

    **自己给容器中添加HttpMessageConverter，只需要将自己的组件注册进容器中（通过@Bean、@Component组件扫描等方法皆可）**

- Automatic registration of `MessageCodesResolver` (see below)：定义错误代码生成规则。

- Automatic use of a `ConfigurableWebBindingInitializer` bean (see below).

  **我们可以配置一个ConfigurableWebBindingInitializer添加到容器来替换默认的。**

  ```
  ConfigurableWebBindingInitializer是初始化WebDataBinder的,它的作用是将请求数据绑定到JavaBean上面，绑定的过程还会涉及使用上面提到的转换器和格式化器等。
  ```

**org.springframework.boot.autoconfigure.web：该包下是web的所有自动配置场景。**



**// 如果你想使用spring boot中对Spring MVC自动配置的这些功能，并且你只是想额外添加（扩展）一些功能**

If you want to keep Spring Boot MVC features, and you just want to add additional [MVC configuration](https://docs.spring.io/spring/docs/4.3.14.RELEASE/spring-framework-reference/htmlsingle#mvc) 

**//  （例如拦截器、格式化器等等）你可以编写自己的继承了WebMvcConfigurerAdapter的配置类，**

(interceptors, formatters, view controllers etc.) you can add your own `@Configuration` class of type 

**// 但是不要标注@EnableWebMvc注解。如果你希望提供RequestMappingHandlerMapping、**

`WebMvcConfigurerAdapter`, but **without** `@EnableWebMvc`. If you wish to provide custom instances of

**// RequestMappingHandlerAdapter或ExceptionHandlerExceptionResolver的自定义实例，**

 `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter` or `ExceptionHandlerExceptionResolver`

**// 你可以声明一个WebMvcRegistrationsAdapter实例提供这样的组件。**

 you can declare a `WebMvcRegistrationsAdapter` instance providing such components.

**// 如果你想全面接管Spring MVC，那么你可以添加你自己的带有@EnableWebMvc注解的配置类。**

If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`.



### 2、扩展SpringMVC

以前我们在使用spring的时候，会做很多配置，例如视图解析、拦截器等等，以前的配置文件如下：

```xml
    <!-- 将/hello路径解析到名为success的视图 -->
	<mvc:view-controller path="/hello" view-name="success"/>
    <!-- 配置拦截器，拦截/hello请求 -->
	<mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/hello"/>
            <bean></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

**编写一个配置类（@Configuration），是WebMvcConfigurerAdapter类型（继承），并且不能标注@EnableWebMvc，这种方式既保留了所有的自动配置，也能用我们扩展的配置**

```java
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }
}
```

原理：

​	1）、WebMvcAutoConfiguration是SpringMVC的自动配置类

​	2）、在做其他自动配置时会导入**@Import(EnableWebMvcConfiguration.class)**	--> 155 行  

```java
    @Configuration
	public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
        
      // 下面这些代码都是父类DelegatingWebMvcConfiguration中的
  
      private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();

	  //从容器中获取所有的WebMvcConfigurer
      @Autowired(required = false)
      public void setConfigurers(List<WebMvcConfigurer> configurers) {
          if (!CollectionUtils.isEmpty(configurers)) {
              this.configurers.addWebMvcConfigurers(configurers);
            	// 一个参考实现，将所有的WebMvcConfigurer相关配置都来一起调用（自己的和默认的）。
              	// WebMvcConfigurerComposite --> 105 -110 行
//            	@Override
//              	public void addViewControllers(ViewControllerRegistry registry) {
//                	for (WebMvcConfigurer delegate : this.delegates) {
//               	 	    delegate.addViewControllers(registry);
//                    }
//              	}
          }
	}
```

​	3）、容器中所有的WebMvcConfigurer都会一起起作用。

​	4）、我们的配置类也会被调用。

​	**总结：SpringMVC的自动配置和我们的扩展配置都会起作用。**



### 3、全面接管SpringMVC；

全面接管意味着**SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己配置，所有的SpringMVC的自动配置都失效了。**

**全面接管需要我们在配置类中添加@EnableWebMvc，如下：**

```java
//使用@EnableWebMvc将完全接管SpringMVC，使spring boot的默认配置全部失效
@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }
}
```

原理：

为什么@EnableWebMvc自动配置就失效了？

1）、@EnableWebMvc的核心，@Import导入了DelegatingWebMvcConfiguration：

```java
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {
```

2）、

```java
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
```

3）、WebMvcAutoConfiguration中的所有自动配置是否生效要先判断容器中是否有WebMvcConfigurationSupport这个组件，如果有，该类不生效：

```java
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class,
		WebMvcConfigurerAdapter.class })
//容器中没有这个组件的时候，这个自动配置类才生效
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
```

4）、@EnableWebMvc将WebMvcConfigurationSupport组件导进容器中。

5）、导入的WebMvcConfigurationSupport只是SpringMVC最基本的功能。



## 5、如何修改SpringBoot的默认配置

通过上述的讲解，我们知道了很多种修改spring boot默认配置的方法，现在对这些修改方法进行总结：

​	1）、SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component）如果有就用用户配置的，如果没有，才自动配置（这是由于spring boot中使用了@ConditionalOnMissBean注解）。另外，有些组件可以同时存在多个（如ViewResolver），那么spring boot会将用户配置的和自己默认的组合起来。

​	2）、在SpringBoot中会有非常多的**xxxConfigurer**帮助我们进行扩展配置

​	3）、在SpringBoot中会有很多的**xxxCustomizer**帮助我们进行定制配置（这种方式在第8章配置嵌入式Servlet容器中有讲解，建议看完第8章再去理解这种方式）



## 6、RestfulCRUD

### 1）、默认访问首页

#### 1、通过在Controller中添加空方法实现

```java
@Controller
public class HelloController {

    /**
     * 当我们访问http://localhost:8080/ 即该项目时，由于spring boot默认会访问静态资源下的index页面，	  *	即public/index.html。如果我们想默认访问模板中的login页面，需要写一个方法，让该方法的返回值让	  *	Thymeleaf模板引擎来解析并返回到模板页面， 即templates下的页面。
     */
    @RequestMapping(value = {"/", "/index.html"})
    public String index(){
        // 让Thymeleaf去解析返回值，前后拼串。前面拼上"/templates/"，后面拼上".html"。
        return "login";
    }
｝
```

#### 2、通过在SpringMVC扩展配置类中实现视图映射的方法addViewControllers()实现

```java
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 / 请求，由Thymeleaf解析到templates/login.html
        registry.addViewController("/").setViewName("login");
    }
}

```

#### 3、通过在配置类中添加WebMvcConfigurerAdapter组件来实现

```java
@Configuration
public class MyConfiguration{
   
    /**
     * 为了让浏览器访问该项目http://localhost:8080/ 能请求到模板中的login.html，我们可以通过在
     * Controller里定义一个空方法让Thymeleaf映射到模板中的页面。但是我们为了访问到模板页面就在
     * Controller中添加一个空方法实在不是什么明智的方法，其实我们可以在之前讲解的SpringMVC的扩展功能配
     * 置类（即本类）中，实现addViewController("/").setViewName("login")视图映射方法来让Thymeleaf帮
     * 我们解析到模板页面（即上面addViewControllers方法）。
     *
     * 除此之外，假如我们现在仅仅是需要完成一个视图映射功能就单独写一个配置类去继承
     * WebMvcConfigurerAdapter也实在不是明智之举，其实就算我们不继承WebMvcConfigurerAdapter，仅仅是
     * 一个普通的配置类，也可以实现视图映射的功能。
     *
     * 我们知道由于所有的WebMvcConfigurerAdapter组件都会一起起作用，那我们何不直接向容器中添加一个
     * WebMvcConfigurerAdapter？我们只要让这个组件实现了视图映射的方法不就可以了吗，这需要通过匿名内部
     * 类的方式来完成，如下：
     */
    @Bean //将组件注册到容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        // 由于所有的WebMvcConfigurerAdapter组件都会一起起作用
        // 那我们就直接向容器中添加一个WebMvcConfigurerAdapter
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
            }
        };
    }
}
```







### 2）、登录页面的国际化

实现步骤：

（1）编写国际化配置文件；

（2）使用ResourceBundleMessageSource管理国际化资源文件

（3）在页面使用fmt:message取出国际化内容



步骤的具体实现：

1、编写国际化配置文件，抽取页面需要显示的国际化消息

![](D:\搜狗输入法\搜狗截图\国际化配置文件.png)



2、SpringBoot自动配置好了管理国际化资源文件的组件

```java
@ConfigurationProperties(prefix = "spring.messages")
public class MessageSourceAutoConfiguration {
    
    /**
	 * Comma-separated list of basenames (essentially a fully-qualified classpath
	 * location), each following the ResourceBundle convention with relaxed support for
	 * slash based locations. If it doesn't contain a package qualifier (such as
	 * "org.mypackage"), it will be resolved from the classpath root.
	 */
    // 默认的基础名为"messages"，也就是说如果我们的配置文件直接放在类路径下并且名为messages.properties
    // 那么我们就可以不做任何配置直接使用Spring Boot的默认国际化配置。
	private String basename = "messages";  
    
    @Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		if (StringUtils.hasText(this.basename)) {
            //设置国际化资源文件的基础名（去掉语言国家代码的）
			messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
					StringUtils.trimAllWhitespace(this.basename)));
		}
		if (this.encoding != null) {
			messageSource.setDefaultEncoding(this.encoding.name());
		}
		messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
		messageSource.setCacheSeconds(this.cacheSeconds);
		messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);
		return messageSource;
	}
```

在配置文件中设置国际化配置文件的基础名：

```properties
spring.messages.basename=i18n.login
```



3）、在页面中（login.html）获取国际化的值

![1525240422004](D:\搜狗输入法\搜狗截图\1525240422004.png)

访问页面看国际化是否成功：

![1525240472327](D:\搜狗输入法\搜狗截图\1525240472327.png)

发现出现乱码，这是由于properties中的中文引起的，我们应该在IDEA中对properties文件做如下设置：

![1525241358917](D:\搜狗输入法\搜狗截图\1525241358917.png)



**最终访问效果：根据浏览器语言设置的信息切换了国际化。**



获取国际化语言的原理：

​	核心是国际化对象Locale（区域信息对象），SpringMVC中的LocaleResolver组件用来获取区域信息对象：

```java
	// 默认配置的就是根据请求头带来的区域信息（即Accept-Language）获取Locale进行国际化
	public class WebMvcAutoConfiguration {
		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
		public LocaleResolver localeResolver() {
			if (this.mvcProperties
					.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
				return new FixedLocaleResolver(this.mvcProperties.getLocale());
			}
			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
			localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
			return localeResolver;
		}
    }

```

4）、点击链接切换国际化

**在登录页面login.html中加上中英文切换的超链接，并且点击时带上请求参数 “ l ”：**

```html
	<p class="mt-5 mb-3 text-muted">© 2017-2018</p>
	<a class="btn btn-sm" th:href="@{/index.html(l='zh_CN')}">中文</a>
	<a class="btn btn-sm" th:href="@{/index.html(l='en_US')}">English</a>
```

**不使用spring boot的默认获取区域的方式（即通过请求头中Accept-Language），我们自己定义一个LocaleResolver对象：**

```java
/**
 * description: 编写自己的区域信息解析器，不是用spring boot默认的（根据请求头Accept-Language）来获
 * 取。自己的区域信息解析器可以通过点击页面最下方的超链接来改变中英文。
 *
 * @author 任伟
 * @date 2018/5/2 14:32
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 如果点击超链接，会带上请求参数l，因此先获取request中的参数l
        String param = request.getParameter("l");
        // 如果没有该参数就使用系统默认的Locale
        Locale locale = Locale.getDefault();
        // 如果参数不为空，那么就用获取到的参数创建一个Locale对象并返回
        if(!StringUtils.isEmpty(param)){
            String[] split = param.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
```

**将我们自己定义的区域信息解析器加到容器中：**

```java
    // 添加我们自己配置的区域信息解析器组件，从而替换spring boot默认的区域解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
```



### 3）、登陆

1.修改login.html页面中form表单提交的action以及对请求方法进行限定：

```html
<form class="form-signin" action="dashboard.html" th:action="@{/user/login}" method="post">
```

2.编写登录的Controller：

```java
@Controller
public class LoginController {

    //POST请求的RequestMapping简化形式。不同请求有不同的简化，例如 @GetMapping。
    @PostMapping(value = "/user/login") 
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        Map<String, Object> map){
        //使用 @RequestParam("xxx")注解，要求请求中必须包含xxx参数，不然会报错。

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            // 登录成功
            return "dashboard";
        }else {
            // 登录失败
            map.put("msg", "用户名或密码错误！");
            return "login";
        }
    }
}
```



**开发期间模板引擎的静态页面（如login.html）修改以后，若想不重启项目就实时生效：**

1）、在spring boot全局配置文件中禁用模板引擎的缓存

```properties
# 禁用缓存
spring.thymeleaf.cache=false 
```

2）、页面修改完成以后ctrl+f9：重新编译，即这个按键：

![1525245656668](D:\搜狗输入法\搜狗截图\1525245656668.png)



3.登录失败提示错误消息：

```html
<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```



### 4）、拦截器进行登陆检查

在LoginController中，当我们登录成功后由于是直接 return "dashboard"，这是一个请求转发，我们知道请求转发的特点是浏览器URL不会改变，因此当我们登录成功后，虽然页面变成了dashboard.html，但是URL依然是http://localhost:8080/user/login，这就导致了如果我们此时F5刷新页面会再次进行表单提交，造成重复提交的问题。

针对这个问题，我们应该将请求转发改为重定向，首先要在**配置类中添加一个视图映射**（上面讲过），如图：

![1525247584000](D:\搜狗输入法\搜狗截图\1525247584000.png)

然后**在LoginController登录成功后重定向到main.html**，模板引擎会将我们将main.html解析为dashboard页面：

```java
	if (!StringUtils.isEmpty(username) && "123456".equals(password)){
    	// 登录成功，为了防止表单重复提交，可以重定向到main.html（已经在
        // MyMvcConfigurer.webMvcConfigurerAdapter()方法中做了视图映射）
            return "redirect:/main.html";
        }
```

​	**这样我们就完成了登录后的重定向，但是这里有个很严重的问题，像我们这样写的话，如果我浏览器直接访问http://localhost:8080/main.html的话可以直接进入登录成功后的页面，这样登录的意义何在？因此我们引出拦截器的概念。**



拦截器的实现步骤：

（1）首先在LoginController中加入HttpSession参数，并且登录成功后，**将已登录的用户放入session域中**，如图：

![1525248490139](D:\搜狗输入法\搜狗截图\1525248490139.png)



（2）编写登录检查的拦截器：

```java

/**
 * 实现登录检查的拦截器，没有登录的用户不能访问后台页面。
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null){
            // 未登录
            request.setAttribute("msg", "请先登录再访问该页面");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }else {
            // 已登录，直接返回true放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

```



（3）注册拦截器

```java
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 注册我们编写的登录检查拦截器，并且拦截"/**"项目路径下的所有请求。
                // 但是排除掉登录页面（主页）和提交登录表单的请求。
                // 另外，以前我们使用SpringMVC的时候还要对css、js等静态资源进行排除，但是这里
                // spring boot已经对静态资源进行了映射，所以我们不需要再进行配置。
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }
```

### 5）、CRUD-员工列表

实验要求：

1）、RestfulCRUD：CRUD满足Rest风格；

Restful风格要求 URI：/资源名称/资源标识，通过HTTP请求方式来区分对资源CRUD操作，看下面对比：

|      | 普通CRUD（uri来区分操作）        | RestfulCRUD       |
| ---- | ----------------------- | ----------------- |
| 查询   | getEmp                  | emp---GET         |
| 添加   | addEmp?xxx              | emp---POST        |
| 修改   | updateEmp?id=xxx&xxx=xx | emp/{id}---PUT    |
| 删除   | deleteEmp?id=1          | emp/{id}---DELETE |

2）、实验的请求架构：

| 实验功能               | 请求URI | 请求方式   |
| ------------------ | ----- | ------ |
| 查询所有员工             | emps  | GET    |
| 查询某个员工(来到修改页面)     | emp/1 | GET    |
| 来到添加页面             | emp   | GET    |
| 添加员工               | emp   | POST   |
| 来到修改页面（查出员工进行信息回显） | emp/1 | GET    |
| 修改员工               | emp   | PUT    |
| 删除员工               | emp/1 | DELETE |

3）、获取员工列表：

我们先将后台主页dashboard.html中员工管理的超链接进行修改，使其发送/emps请求，如图：

![1525313046642](D:\搜狗输入法\搜狗截图\1525313046642.png)

然后我们编写EmployeeController进员工CRUD请求进行处理，如下：

```java
@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 查询所有员工
     * @return 返回员工列表emp/list.html
     */
    @GetMapping("/emps")
    public String getEmpList(Model model){
        // 查出所有员工
        Collection<Employee> employees = employeeDao.getAll();
        // 将查询的结果放在请求域中
        model.addAttribute(employees);

        // Thymeleaf默认会拼串：classpath:/templates/emp/xxx.html
        return "emp/list";
    }
}
```

编写完成后我们通过浏览器来测试下，如图：

![1525313214256](D:\搜狗输入法\搜狗截图\1525313214256.png)

![1525313297495](D:\搜狗输入法\搜狗截图\1525313297495.png)

我们看到，成功的查询出了员工列表，并且我们刚才编写的Controller是将请求转发到emp/list.html进行员工列表的展示，但是我们发现这两个页面左边的菜单栏和上面的搜索框样式是完全一样的，但是我们却要在dashboard和list两个页面中写两次同样的代码，基于此Thymeleaf为我们提供了公共页面元素抽取的功能，将相同的页面可以抽取成公共部分。



#### thymeleaf公共页面元素抽取

```html
1、抽取公共片段，将公共片段命名为copy（名字任意取）
<div th:fragment="copy">
	&copy; 2011 The Good Thymes Virtual Grocery
</div>

2、引入公共片段
<div th:insert="~{footer :: copy}"></div>
上面的~{}中有下面两种写法：
~{templatename::selector}	模板名（即定义抽取片段的那个静态资源文件名）::选择器（即CSS选择器语法）
~{templatename::fragmentname}	模板名::片段名

```

我们先在dashboard.html中抽取顶栏的公共片段，如图：

![1525313999511](D:\搜狗输入法\搜狗截图\1525313999511.png)

在list.html中引入公共片段，如图：

![1525314476783](D:\搜狗输入法\搜狗截图\1525314476783.png)



修改后的默认效果，由于我们引入公共片段的时候将th:insert放在了<div>标签中，因此引入<nav>标签默认也在<div>标签中，如图：

![1525314938919](D:\搜狗输入法\搜狗截图\1525314938919.png)

但是由于我们引用的片段本身是没有包围在<div>中的，虽然目前样式看起来没什么问题，但是不保证以后元素多起来以后不出问题，因此这里我们需要做一些改变，其实Thymleaf中不只能使用th: insert来引入公共片段，请看下面讲解。



Thymeleaf提供了三种引入公共片段的th属性：

**th:insert**：将公共片段整个插入到声明引入的元素中（这里就是div）

**th:replace**：将声明引入的元素（这里就是div）替换为公共片段

**th:include**：将被引入的片段的内容包含进这个标签中



```html
<!-- 官方文档给出的举例说明 -->
<footer th:fragment="copy">
	&copy; 2011 The Good Thymes Virtual Grocery
</footer>

<!-- 三种引入方式 -->
<div th:insert="templates :: copy"></div>
<div th:replace="templates :: copy"></div>
<div th:include="templates :: copy"></div>

<!-- 三种引入方式在页面上的最终效果 -->

<!-- 使用th: insert会将引入的片段放入div中 -->
<div>
    <footer>
    	&copy; 2011 The Good Thymes Virtual Grocery
    </footer>
</div>

<!-- 使用th: replace会去掉div标签，最终效果就是引入什么就是什么 -->
<footer>
	&copy; 2011 The Good Thymes Virtual Grocery
</footer>

<!-- 使用th: include是用div标签将片段的外层标签footer覆盖掉 -->
<div>
	&copy; 2011 The Good Thymes Virtual Grocery
</div>
```



由于我们想去掉list.html中引入片段外层的<div>标签，根据上面的讲解，我们应该选择th: replace来引入片段，修改后我们再来检查页面元素，如图：

![1525315837876](D:\搜狗输入法\搜狗截图\1525315837876.png)

发现<nav>外层的<div>确实已经去掉了。

**另外要说明一点，如果我们使用 th: insert，th: replace，th: include这三种形式来引入片段的时候，~{}可以省略，即可以写成<div th: replace = "dashboard::topbar">**

**但是如果我们将片段的引入不写在标签内，因此就不能使用th: xxx这三种属性，我们只能通过行内引用来引用片段，对于行内写法不能省略~{}，即 [[~{dashboard::topbar}]] 或 [(~{dashboard::topbar})]**



对于左侧的菜单栏，我们也进行片段抽取和引用，但是这里我们使用另一种方式来完成，即"templatename::selector"  模板名::选择器的方式。在dashboard.html中定义片段不再使用 th: fragment，而是为要抽取的片段定义id属性（如果本身就有id则不需定义，直接引用即可），如图：

![1525317735080](D:\搜狗输入法\搜狗截图\1525317735080.png)

在list.html中对该片段进行引用，CSS选择器对id属性的引用语法是#id，如图：

![1525317841459](D:\搜狗输入法\搜狗截图\1525317841459.png)



为了便于其他页面对片段进行引用，我们现在将所有的公共元素都抽取到commons/bar.html中，如图：

![1525319162533](D:\搜狗输入法\搜狗截图\1525319162533.png)

在dashboard.html中引入topbar和sidebar，如图：

![1525319209164](D:\搜狗输入法\搜狗截图\1525319209164.png)

在list.html中引入topbar和sidebar，如图：

![1525319251470](C:\Users\45900\AppData\Local\Temp\1525319251470.png)



### 6）、CRUD-员工添加

添加页面add.html的form表单模板：

```html
<form>
    <div class="form-group">
        <label>LastName</label>
        <input type="text" class="form-control" placeholder="zhangsan">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" class="form-control" placeholder="zhangsan@atguigu.com">
    </div>
    <div class="form-group">
        <label>Gender</label><br/>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender"  value="1">
            <label class="form-check-label">男</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender"  value="0">
            <label class="form-check-label">女</label>
        </div>
    </div>
    <div class="form-group">
        <label>department</label>
        <select class="form-control">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
        </select>
    </div>
    <div class="form-group">
        <label>Birth</label>
        <input type="text" class="form-control" placeholder="zhangsan">
    </div>
    <button type="submit" class="btn btn-primary">添加</button>
</form>
```

1.当点击“添加员工”按钮时，应该跳转到添加页面（即add.html），编写相应的Controller，如下：

```java
    /**
     * 来到添加员工的页面。
     * @return 返回到添加页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 查出所有的部门，在添加页面进行显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 返回添加页面emp/add.html
        return "emp/add";
    }
```

2.在添加页面点击提交的时候，会将请求的数据提交到后台，由后台调用employeeDao.save(employee)将此员工添加，添加成功后应该请求“localhost:8080/emps”重定向到员工列表，编写此Controller如下：

```java
    /**
     * 添加员工，通过POST请求方式提交。
     * @param employee SpringMVC自动将请求参数和入参对象的属性进行绑定，要求请求参数的参数名
     *                 和javaBean中的属性名要一致。
     * @return 员工添加完成后，应该返回员工列表，而且应该是通过发送/emps请求来到员工列表页面，
     *         这就要求我们的返回值不能让Thymeleaf以为我们是要返回到某个静态资源，因此这里我们
     *         使用重定向来到/emps请求到的页面下（就相当于重新发送以便"localhost:8080/emps"请求），
     *         其中"/"表示当前项目路径，使用重定向应该以redirect:开始。
     *         另外，请求转发是以forward:开始。
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        // 保存员工数据
        employeeDao.save(employee);
        // 重定向到员工列表
        return "redirect:/emps";
    }
```



3.在添加页面提交数据的时候需要注意一个问题，就是日期的格式，由于SpringMVC会将提交的日期参数最终转为Date类型然后映射到javaBean中，因此这里就涉及到日期的格式化和转换问题，我们日常书写日期的格式会多种多样，例如 2017-12-12，2017/12/12，2017.12.12等等，会用各种分隔符进行分割。这里说下SpringMVC默认用的是"/"进行日期分割，如图：

![1525327724898](D:\搜狗输入法\搜狗截图\1525327724898.png)

![1525327779805](D:\搜狗输入法\搜狗截图\1525327779805.png)

![1525327806823](D:\搜狗输入法\搜狗截图\1525327806823.png)

我们可以通过spring boot的配置文件来修改此默认值，如图：

![1525327983412](D:\搜狗输入法\搜狗截图\1525327983412.png)

最终效果：

![1525328748927](D:\搜狗输入法\搜狗截图\1525328748927.png)

![1525328763930](D:\搜狗输入法\搜狗截图\1525328763930.png)

### 7）、CRUD-员工修改

修改add.html中的form表单，使其成为修改、添加二合一表单（区别是添加不需要回显，修改需要回显）：

```html
<!--需要区分是员工修改还是添加；-->
<form th:action="@{/emp}" method="post">
    <!--发送put请求修改员工数据-->
    <!--
1、SpringMVC中配置HiddenHttpMethodFilter;（SpringBoot自动配置好的）
2、页面创建一个post表单
3、创建一个input项，name="_method";值就是我们指定的请求方式
-->
    <input type="hidden" name="_method" value="put" th:if="${emp!=null}"/>
    <input type="hidden" name="id" th:if="${emp!=null}" th:value="${emp.id}">
    <div class="form-group">
        <label>LastName</label>
        <input name="lastName" type="text" class="form-control" placeholder="zhangsan" th:value="${emp!=null}?${emp.lastName}">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input name="email" type="email" class="form-control" placeholder="zhangsan@atguigu.com" th:value="${emp!=null}?${emp.email}">
    </div>
    <div class="form-group">
        <label>Gender</label><br/>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" value="1" th:checked="${emp!=null}?${emp.gender==1}">
            <label class="form-check-label">男</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" value="0" th:checked="${emp!=null}?${emp.gender==0}">
            <label class="form-check-label">女</label>
        </div>
    </div>
    <div class="form-group">
        <label>department</label>
        <!--提交的是部门的id-->
        <select class="form-control" name="department.id">
            <option th:selected="${emp!=null}?${dept.id == emp.department.id}" th:value="${dept.id}" th:each="dept:${depts}" th:text="${dept.departmentName}">1</option>
        </select>
    </div>
    <div class="form-group">
        <label>Birth</label>
        <input name="birth" type="text" class="form-control" placeholder="zhangsan" th:value="${emp!=null}?${#dates.format(emp.birth, 'yyyy-MM-dd HH:mm')}">
    </div>
    <button type="submit" class="btn btn-primary" th:text="${emp!=null}?'修改':'添加'">添加</button>
</form>
```

点击员工列表的编辑按钮，会转到员工修改页面，并将员工信息回显，编写此Controller：

```java
    /**
     * 来到修改页面（即员工添加页面add.html），将员工信息查出并在页面回显。
     * 我们在@GetMapping("/emp/{id}")中写的"/{id}"是路径变量，可以通过@PathVariable获取。
     * @param id 路径变量，员工的id。
     * @param model 用于在页面中取出数据的模型对象。
     * @return 返回到修改页面（add.html修改和添加二合一的页面）
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        // 查出路径变量的员工信息
        Employee employee = employeeDao.get(id);
        // 将员工信息保存到session中用于回显
        model.addAttribute("emp", employee);
        //查出部门列表并保存
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 返回到修改页面（add.html修改和添加二合一的页面）
        return "emp/add";
    }
```

在员工修改页面编辑完员工修改信息后，点击“修改”按钮会发送PUT请求来修改员工信息，编写处理此请求的Controller：

```java
    /**
     * 修改员工，通过Put请求方式提交。
     * @param employee 修改后的员工信息
     * @return 返回员工列表
     */
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
```





### 8）、CRUD-员工删除

修改list.html中的删除按钮，通过js事件的方式完成删除请求（DELETE请求方式）的发送：

```html
...省略上方...

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <h2>
        <a class="btn btn-sm btn-success" href="emp" th:href="@{/emp}">添加员工</a>
    </h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>#</th>
                    <th>lastName</th>
                    <th>email</th>
                    <th>gender</th>
                    <th>department</th>
                    <th>birth</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="emp : ${emps}">
                    <td th:text="${emp.id}"></td>
                    <td>[[${emp.lastName}]]</td>
                    <td th:text="${emp.email}"></td>
                    <td th:text="${emp.gender} == 0 ? '女' : '男'"></td>
                    <td th:text="${emp.department.departmentName}"></td>
                    <td th:text="${#dates.format(emp.birth, 'yyyy-MM-dd HH:mm:ss')}"></td>
                    <td>
                        <a class="btn btn-sm btn-primary" th:href="@{/emp/} + ${emp.id}">
                            编辑
                        </a>
                        <button th:attr="del_uri=@{/emp/} + ${emp.id}" 
                                class="btn btn-sm btn-danger deleteBtn">
                            删除
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</main>
<form id="deleteEmpForm" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>

...省略中间...

<script>
    $(".deleteBtn").click(function(){
        //删除当前员工的
        $("#deleteEmpForm").attr("action",$(this).attr("del_uri")).submit();
        return false;
    });
</script>
```

当点击某个员工删除按钮后，会通过发送DELETE请求将此员工删除，编写处理此请求的Controller：

```java
    /**
     * 删除员工，通过DELETE请求方式提交请求。
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
```





## 7、错误处理机制

### 1）、SpringBoot默认的错误处理机制

默认效果：

​		1）、浏览器访问，会返回一个默认的错误页面，如图：

![](images/搜狗截图20180226173408.png)

  浏览器发送请求的请求头：

![](images/搜狗截图20180226180347.png)

​		2）、如果是其他客户端（这里我们使用的是Postman），默认响应一个json数据

![](images/搜狗截图20180226173527.png)

​		![](images/搜狗截图20180226180504.png)



原理：参照ErrorMvcAutoConfiguration.java 错误处理的自动配置。

​	ErrorMvcAutoConfiguration这个配置类给容器中添加了以下重要的4个组件，如图：

![1525336128691](D:\搜狗输入法\搜狗截图\1525336128691.png)

![1525336184253](D:\搜狗输入法\搜狗截图\1525336184253.png)

**除了图示的这4个组件外，该配置类还添加了其他的组件，这里我们着重讲解这4个：**



**1、DefaultErrorAttributes：**

```java
// 帮我们在页面共享信息
@Override
	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
			boolean includeStackTrace) {
		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("timestamp", new Date());
		addStatus(errorAttributes, requestAttributes);
		addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
		addPath(errorAttributes, requestAttributes);
		return errorAttributes;
	}
```



**2、BasicErrorController：处理默认/error请求**

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {
    
    @RequestMapping(produces = "text/html") // 产生html类型的数据，浏览器发送的请求来到这个方法处理
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
        
         // 通过调用resolveErrorView()方法解析出一个ModelAndView，它里面包含页面地址和页面内容
         // ModelAndView对象中的内容最终决定去哪个页面作为错误页面	
         // resolveErrorView()方法具体如何解析，看下面讲解的错误处理步骤--(1)响应页面
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
     	return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
	}

	@RequestMapping
	@ResponseBody    // 产生json数据，其他客户端来到这个方法处理；
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
```

​	如果是浏览器发送的请求，发生错误后通过BasicErrorController.errorHtml()方法进行处理；如果是其他客户端(如Postman)发送的请求，发生错误后通过BasicErrorController.error()方法进行处理。

​	那么它怎么知道请求是浏览器发的还是其他客户端发的？

​	我们看上边浏览器和客户端发送请求时的请求头（Request Header）的图片，浏览器发送的请求头的Accept的value为 “text/html,application/xhtml+xml,application/xml”，也就说它可以接收这些格式的返回形式，但是它会优先接收text/html（即页面）；而Postman的请求头的Accept的value为"\*/*",它没有说优先接收"text/html"。正因为如此来判断是浏览器还是其他客户端发送的请求。





**3、ErrorPageCustomizer：**

```java
public class ErrorProperties {

	/**
	 * 系统出现错误以后来到error请求进行处理。
	 * 类似于在web.xml注册的错误页面规则。
	 */
	@Value("${error.path:/error}")
	private String path = "/error";

	...
｝
```



**4、DefaultErrorViewResolver：**错误视图解析器，会最终解析出ModelAndView对象，决定去哪个页面作为错误页面

```java
@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
			Map<String, Object> model) {
		ModelAndView modelAndView = resolve(String.valueOf(status), model);
		if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
			modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
		}
		return modelAndView;
	}

	private ModelAndView resolve(String viewName, Map<String, Object> model) {
        //默认SpringBoot可以找到一个页面，即"error/404"
		String errorViewName = "error/" + viewName;
        
        //模板引擎可以解析这个页面地址就用模板引擎解析
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders
				.getProvider(errorViewName, this.applicationContext);
		if (provider != null) {
            //模板引擎可用的情况下会通过模板引擎来解析errorViewName指定的视图
            //例如我们使用的Thymeleaf模板引擎解析"error/404"的话就会拼串
            //拼串结果是： "classpath:templates/" + errorViewName + ".html"
			return new ModelAndView(errorViewName, model);
		}
        //如果模板引擎不可用，就在静态资源文件夹下找errorViewName对应的页面，即"error/404.html"
        //spring boot的静态资源文件夹我们前边讲过，可以是public、resources、static等等。
		return resolveResource(errorViewName, model);
	}
```



**错误处理步骤：**

一但系统出现4xx或者5xx之类的错误，**ErrorPageCustomizer**就会生效（定制错误的响应规则），就会来到/error请求，然后就会被**BasicErrorController**处理，上边说过BasicErrorController中有个方法，分别是针对浏览器或其他客户端的处理方法，因此BasicErrorController的处理结果就会有两种：

​	1）响应页面：下面代码的注释中说到通过错误视图解析器（ErrorViewResolver）来解析ModelAndView对象，但是ErrorViewResolver是什么呢？其实它就是上面的组件4、DefaultErrorViewResolver。所以我们说，最终去哪个页面是由**DefaultErrorViewResolver**解析得到的：

```java
// 下面这段代码就是上面BasicErrorController中resolveErrorView()方法解析出ModelAndView对象的具体逻辑
protected ModelAndView resolveErrorView(HttpServletRequest request,
      HttpServletResponse response, HttpStatus status, Map<String, Object> model) {

    // 遍历所有的错误视图解析器（ErrorViewResolver）来尝试解析出ModelAndView
    // 如果解析出了就返回ModelAndView，所有错误视图解析器（ErrorViewResolver）都没解析出就返回null
   for (ErrorViewResolver resolver : this.errorViewResolvers) {
      ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
      if (modelAndView != null) {
         return modelAndView;
      }
   }
   return null;
}
```

​	2）响应json字符串



### 2）、自定义错误响应

#### 	**1、如何定制错误的页面；**

​	**（1）有模板引擎的情况下-->error/状态码.html:** 		

​	将我们自己编写的**错误页面的静态资源文件**命名为【状态码.html】（例如404.html），并将其放置在模板引擎路径下的error文件夹下，例如在我们这个项目中就放在"/templates/error/404.html"路径。项目只要发生此状态码的错误就会来到对应的状态码页面。

​	另外，DefaultErrorViewResolver中有这样的定义，如图：

![1525353704237](D:\搜狗输入法\搜狗截图\1525353704237.png)

​	它的意思是我们可以在error文件夹下定义"4xx.html"和"5xx.html"，所有以4开头的错误都可以匹配4xx.html，所有以5开头的错误都可以匹配5xx.html。当然，精确匹配优先，也就是说如果报了405的错误，正好有405.html的话优先选择405.html作为返回的错误页面，没匹配到精确的405.html的话才会选择4xx.html。		

​	在最终返回错误页面之前还有一步，就是通过DefaultErrorAttributes向错误页面中存放一些信息，这些信息包括：

​		**timestamp：时间戳**

​		**status：状态码**

​		**error：错误提示**

​		**exception：异常对象**

​		**message：异常消息**

​		**errors：JSR303数据校验的错误都在这里**

​	下面我们就在404.html和4xx.html中获取这些信息其中的一部分，如图（两个页面代码一样，不重复贴图了）：	![1525354675218](D:\搜狗输入法\搜狗截图\1525354675218.png)

页面效果：

![1525354807736](D:\搜狗输入法\搜狗截图\1525354807736.png)





​	**（2）没有模板引擎（或者模板引擎找不到这个错误页面），那么默认会到静态资源文件夹下找。**

​	**（3）以上都没有错误页面，就是默认来到SpringBoot默认的错误提示页面。**



#### 	2、如何定制错误的json数据

首先为了测试错误的json数据，我们先来自定义一个异常，如下：

```java
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
```

然后修改HelloController.hello()方法，如下：

```java
@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("user") String user){
        // 为了测试返回自定义错误的json数据，故在此抛出异常
        if (user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "hello world !";
    }
}
```

我们通过Postman发送请求"/hello?user=aaa"，让程序抛出异常，得到的json错误响应数据如图：

![1525364823395](D:\搜狗输入法\搜狗截图\1525364823395.png)

这些字段都是spring boot的默认定制，如果我们不想要这些信息应该如何自己定制呢？

**（1）通过自定义异常处理器来返回自定义json数据：**

```java
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * @param e @ExceptionHandler捕获的异常会传给参数e
     * @return 返回Map的json格式
     */
    @ResponseBody // 由于我们要返回的json数据，故使用此注解
    @ExceptionHandler(UserNotExistException.class) // 此注解用于捕获value中定义的异常，value的值是Throwable[]
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;
    }
}
```

此时我们再次用Postman发送请求，让程序抛出异常，如图：

![1525366145418](C:\Users\45900\AppData\Local\Temp\1525366145418.png)

发现这次返回的错误json数据成了我们自定的了，但是此时我们再用浏览器发送这个请求试试：

![1525366205616](C:\Users\45900\AppData\Local\Temp\1525366205616.png)

​	我们发现浏览器不再返回错误页面，而是返回自定义的json错误数据，这是因为当发生UserNotExistException异常时我们自定义的异常处理器MyExceptionHandler会捕获到该异常并通过@ResponseBody返回json数据（我们自动义的Map）。从另外的角度来说，由于错误信息是我们自己定制的而不是使用的spring boot默认的，因此spring boot无法为我们找到错误页面，因此浏览器访问时发生错误也返回错误的json数据。

​	但这其实并不是我们想要的，因为这种方式无法自适应，即无法实现浏览器访问的话返回错误页面，其他客户端访问的时候返回错误的json数据。

​	回想我们上边我们讲的错误处理自动配置类给容器添加的4个组件，其中BasicErrorController是可以做到自适应的，它是根据请求头的Accept来判断是浏览器还是其他客户端的。那我们就想了，我们在异常处理器中捕获到异常后可不可以不直接返回json数据，而是转发给BasicErrorController让它进行自适应处理呢？没错，这就是下面要说的第二种方式。



**（2）转发到/error，让BasicErrorController进行自适应处理：**

```java
@ExceptionHandler(UserNotExistException.class)
public String handleException(Exception e){
	Map<String, Object> map = new HashMap<>();
	map.put("code", "user.notexist");
	map.put("message", e.getMessage());
	// 转发到/error，让BasicErrorController进行自适应处理
	return "forward:/error";
}
```



我们再通过浏览器进行求情，如图：

![1525367399435](D:\搜狗输入法\搜狗截图\1525367399435.png)

我们发现已经能返回错误页面了，但是返回的却并不是我们自己定制的页面，这是由于状态码status的原因，我们看到上图中status=200，所以在错误页面解析的时候无法找到相对应的错误页面进行返回。

因此我们需要给错误信息设置4xx或5xx的状态码，我们就通过模拟BasicErrorController中设置状态码的方式来设置，如图：

![1525367734763](D:\搜狗输入法\搜狗截图\1525367734763.png)

![1525367780439](D:\搜狗输入法\搜狗截图\1525367780439.png)

定义自己的错误状态码：

```java
@ExceptionHandler(UserNotExistException.class)
public String handleException(Exception e){
	Map<String, Object> map = new HashMap<>();
	// 模拟BasicErrorController中定义状态码的方式来自定义错误的状态码
    request.setAttribute("javax.servlet.error.status_code", 500);
	map.put("code", "user.notexist");
	map.put("message", e.getMessage());
	// 转发到/error，让BasicErrorController进行自适应处理
	return "forward:/error";
}
```

再次用浏览器进行请求，效果如图：

![1525368001362](D:\搜狗输入法\搜狗截图\1525368001362.png)

但是此时我们还有一点瑕疵，就是通过Postman访问时，如图：

![1525368141085](D:\搜狗输入法\搜狗截图\1525368141085.png)

我们发现接收到的json错误信息根本不是我们在异常处理器MyExceptionHandler中写出去的那些，在MyExceptionHandler中我们只写出了状态码、code、message这三个，和这个返回的信息不一致，如图：

![1525368274384](D:\搜狗输入法\搜狗截图\1525368274384.png)

这个问题要怎么解决呢？请看下面的讲解。



**（3）将我们的定制数据携带出去**

​	上面（2）中已经讲解过，当程序出现错误以后，会来到**/error**请求被**BasicErrorController**处理，响应出去的错误页面（或json串）中携带的数据是通过调用**getErrorAttributes()**方法得到的，如图：

![1525399338560](D:\搜狗输入法\搜狗截图\1525399338560.png)

而**getErrorAttributes()**方法是在**AbstractErrorController（实现了ErrorController接口）**中定义的，如图：

![1525399581478](D:\搜狗输入法\搜狗截图\1525399581478.png)

为什么这里要强调它实现了**ErrorController**接口呢？我们需要回到最上边**ErrorMvcAutoConfiguration**给容器添加的4个组件之**BasicErrorController组件**：

![1525400329957](D:\搜狗输入法\搜狗截图\1525400329957.png)

这是在开始讲解时遗留的一个问题，**@ConditionalOnMissingBean(value = ErrorController.class)**决定了只有在容器中没有ErrorController及其后代的时候才会给容器中添加**BasicErrorController组件**，那现在我们就有思路了，可以通过下面方式1来携带我们自己定义的错误数据：

1、编写一个ErrorController的实现类（或是AbstractErrorController的子类）放在容器中，由我们来**getErrorAttributes()**方法，但是这样实在太麻烦了，我们来探究还有没有其他的方法。



我们来看**AbstractErrorController**中的**getErrorAttributes()**方法，它的实现是通过**this.errorAttributes.getErrorAttributes()**来实现的，如图：

![1525401254665](D:\搜狗输入法\搜狗截图\1525401254665.png)

那么**errorAttributes**是什么呢？我们点过来看，如图：

![1525401503608](D:\搜狗输入法\搜狗截图\1525401503608.png)

其实**ErrorAttributes**就是**ErrorMvcAutoConfiguration**给容器注册的四大组件之**DefaultErrorAttributes**，如图：

![1525401646961](D:\搜狗输入法\搜狗截图\1525401646961.png)

我们可以看到，当容器中没有**ErrorAttributes**及其后代的时候，**ErrorMvcAutoConfiguration**会给容器中注册一个默认的组件即**DefaultErrorAttributes**，也正是在这个组件中为错误页面（或json数据）定制的错误信息。基于此，我们就有了第2种方式来携带我们自己定义的错误数据：

2、页面上携带的错误数据，或json返回的错误数据，究其根本都是通过**errorAttributes.getErrorAttributes()**方法得到的，如果容器中没有**ErrorAttributes**及其后代，那么会注册一个默认**DefaultErrorAttributes**定制错误数据，如果容器中有**ErrorAttributes**及其后代，那么就会使用该组件，便不会给容器中注册默认的**DefaultErrorAttributes**，因此我们要做的就是自定义**ErrorAttributes**：

```java
/**
 * description: 定义ErrorAttributes接口的实现，会替换掉给容器中注册的默认的DefaultErrorAttributes。
 * 因为由于@ConditionalOnMissingBean(value = ErrorAttributes.class)，如果容器中没有ErrorAttributes
 * 接口实现的话，ErrorMvcAutoConfiguration就会给容器注册一个默认的DefaultErrorAttributes。
 * 所以我们自己来实现一个，不用默认的DefaultErrorAttributes。
 *
 * @author 任伟
 * @date 2018/5/4 10:49
 */

@Component // 需要将该自定义组件加在容器中才能起作用
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        // 我们调用父类DefaultErrorAttributes.getErrorAttributes()方法返回的Map就是我们不定义该组件
        // 情况下默认返回的那些错误数据，map中包含timestamp、status、error、path等。
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        // 我们可以在这个map的基础上进行错误信息的自定义，比如remove掉某个属性，
        // 或者添加一些我们想要的属性，如下
        map.remove("status");
        map.put("name", "任伟");
        return map;
    }
}
```



最终的效果：响应是自适应的，可以通过定制ErrorAttributes改变需要返回的内容，测试浏览器访问，如图：

![1525403021555](D:\搜狗输入法\搜狗截图\1525403021555.png)

返回错误页面，没问题，接下来测试Postman发送请求：

![1525403065840](D:\搜狗输入法\搜狗截图\1525403065840.png)

不只有了我们在map中添加的"name=任伟",而且也去除了status字段，达成了自定义错误信息数据的需求！

但这里还有最后一个问题没有解决！我们之前自定义的异常处理器中在map中存放了两个字段code和message，希望在返回错误数据的时候进行返回，但是现在并没有返回：

![1525404594843](D:\搜狗输入法\搜狗截图\1525404594843.png)

其实我们自定义的**MyErrorAttributes**覆写的方法**getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace)** 中的**RequestAttributes** 参数就是包装了**HttpReqquest**，因此我们可以将异常处理器想要返回的字段的map放入request域中，如图：

![1525403848615](D:\搜狗输入法\搜狗截图\1525403848615.png)

由于MyErrorAttributes.getErrorAttributes()方法返回的Map就是最终客户端得到的错误数据返回，因此它是最后一个步骤，肯定是要后于异常处理器执行的，因此异常处理器中放入request域中的数据在这里是可以取到的，如图：

![1525404347399](D:\搜狗输入法\搜狗截图\1525404347399.png)

其中**requestAttributes.getAttribute("extMap", 0)**方法的第二个参数0是一个枚举值，该参数位置需要传从什么域中获取，其中0代表Request域。

​	我们再通过Postman进行测试发现异常处理器中设置的两个字段也都返回了，至此所有问题都已经解决了，如图：

![1525404650175](D:\搜狗输入法\搜狗截图\1525404650175.png)

​	**最后，补充一句，这些Request域中的字段不只能在客户端中进行返回，在页面我们也都可以获取到，这里不做演示了。**



## 8、配置嵌入式Servlet容器

SpringBoot默认使用Tomcat作为嵌入式的Servlet容器：

![](images/搜狗截图20180301142915.png)



**经过简单的分析，我们这里有以下几个问题：**

### 1）、如何定制和修改Servlet容器的相关配置

有以下两种方式修改相关配置：



**1、在配置文件中修改和server相关的配置**，其实最终修改的就是ServerProperties类中的属性：

```properties
# 通用的Servlet容器设置，server.xxx 例如：
server.port=8081
server.context-path=/crud

# Tomcat的设置，server.tomcat.xxx，例如：
server.tomcat.uri-encoding=UTF-8
```

​	通用的配置其实就是修改**ServerProperties**类中的属性值；而Tomcat的设置，其实是因为**ServerProperties**中封装了一个Tomcat对象类型的属性，对Tomcat的设置就是修改Tomcat对象中的属性，如图：

![1525413565945](D:\搜狗输入法\搜狗截图\1525413565945.png)

​	而这个Tomcat对象其实就是**ServerProperties**中的一个静态内部类，如图：

![1525413646750](D:\搜狗输入法\搜狗截图\1525413646750.png)





**2、编写一个EmbeddedServletContainerCustomizer**：嵌入式的Servlet容器的定制器，来修改Servlet容器的配置

```java
@Configuration
public class MyServerConfigurer{
    /** 
     * 通过编写嵌入式的Servlet容器的定制器来定制Servlet的相关配置，这种方式配置的Servlet容器和
     * 配置文件中通过 server.xxx = yyy 效果是一样的，因为配置文件中的配置最终其实就是修改
     * ServerProperties类中的属性，而ServerProperties其实就是实现的
     * EmbeddedServletContainerCustomizer接口。
     * 另外要注意，一定要将这个组件加入到容器中才能起作用，因此要加上@Bean注解。
     */
    @Bean
    public EmbeddedServletContainerCustomizer myEmbeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                // container参数就是嵌入式的Servlet容器相关的规则
                container.setPort(8083);
            }
        };
    }
}
```

其实配置文件的配置最终底层的实现也是通过实现EmbeddedServletContainerCustomizer的customize()方法来实现的，如图：

![1525414637052](D:\搜狗输入法\搜狗截图\1525414637052.png)



### 2）、注册Servlet三大组件（Servlet、Filter、Listener）

由于SpringBoot默认是以jar包的方式启动嵌入式的Servlet容器来启动SpringBoot的web应用，没有web.xml文件，因此我们不能通过以前在web.xml中注册三大组件的方式进行注册。

spring boot注册三大组件的方式：

#### 1.注册Servlet

我们先来编写一个Servlet，如下：

```java
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello MyServlet");
    }
}
```

给容器中注册ServletRegistrationBean组件从而来注册Servlet组件：

```java
/**
 * 给容器中注册ServletRegistrationBean组件，
 * 该组件中包含我们要注册的Servlet以及该Servlet要拦截的路径
 */
@Bean
public ServletRegistrationBean myServletRegistrationBean(){
   return new ServletRegistrationBean(new MyServlet(), "/myServlet");
}
```

最终效果：

![1525416972721](D:\搜狗输入法\搜狗截图\1525416972721.png)



#### 2.注册Filter

编写自定义的Filter：

```java
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        System.out.println("MyFilter process...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
```

给容器中注册FilterRegistrationBean组件从而来注册Filter组件：

```java
/**
 * 给容器中注册FilterRegistrationBean组件，
 * 该组件中包含我们要注册的Filter以及要过滤的路径
 */
@Bean
public FilterRegistrationBean myFilterRegistrationBean(){
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    // 设置要注册的Filter
    registrationBean.setFilter(new MyFilter());
    // 设置要过滤的路径
    registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
    return registrationBean;
}
```



#### 3.注册Listener

编写Listener：

```java
public class Mylistener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("监听到了应用启动...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听到了应用销毁...");
    }
}
```



给容器中注册ServletListenerRegistrationBean组件从而注册Listener组件：

```java
/**
 * 给容器中注册ServletListenerRegistrationBean组件，
 * 该组件中包含我们要注册的Listener
 */
@Bean
public ServletListenerRegistrationBean myServletListenerRegistrationBean(){
    return new ServletListenerRegistrationBean<>(new Mylistener());
}
```



我们来看一个spring bott中的例子，SpringBoot帮我们自动SpringMVC的时候，自动的注册SpringMVC的前端控制器**DIspatcherServlet**，在**DispatcherServletAutoConfiguration**中：

```java
@Bean(name = DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
@ConditionalOnBean(value = DispatcherServlet.class, name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
public ServletRegistrationBean dispatcherServletRegistration(
      DispatcherServlet dispatcherServlet) {
   ServletRegistrationBean registration = new ServletRegistrationBean(
         dispatcherServlet, this.serverProperties.getServletMapping());
    // 默认拦截的配置为"/"即拦截包括静态资源在内的所有请求，但是不拦截jsp请求。
    // 我们之前在web.xml中配置DispatcherServlet时配置拦截路径为"/*"，它会拦截jsp。
    // 可以通过server.servletPath来修改SpringMVC前端控制器（即DispatcherServlet）默认拦截的请求路径
    
   registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
   registration.setLoadOnStartup(
         this.webMvcProperties.getServlet().getLoadOnStartup());
   if (this.multipartConfig != null) {
      registration.setMultipartConfig(this.multipartConfig);
   }
   return registration;
}
```



### 3）、替换为其他嵌入式Servlet容器

我们在第8.1中编写的嵌入式的Servlet容器的定制器**myEmbeddedServletContainerCustomizer**，实现customize()方法时候，有一个参数ConfigurableEmbeddedServletContainer，我们说过它就是嵌入式的Servlet容器相关的规则的接口，因此我们能够推断出，具体的Servlet容器，一定是实现了这个接口，我们通过快捷键F4来看它向下的继承树，如图：

![1525429108207](D:\搜狗输入法\搜狗截图\1525429108207.png)

我们发现在它的实现类中，有三种Servlet容器的实现，分别是：**Tomcat、Jetty、Undertow**。



spring boot默认支持三种Servlet容器：



Tomcat（默认使用）**

由于starter-web中默认引用了starter-tomcat，所以默认使用Tomcat作为Servlet容器，如图：

![1525429348636](D:\搜狗输入法\搜狗截图\1525429348636.png)

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
    <!-- 引入web模块默认就是使用嵌入式的Tomcat作为Servlet容器 -->
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```



**Jetty**

如果想切换成Jetty，我们需要先将starter-Tomcat排除掉，然后引入starter-jetty:

```xml
<!-- 引入web模块 -->
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
   <!-- 将starter-Tomcat排除掉 -->
   <exclusions>
      <exclusion>
         <artifactId>spring-boot-starter-tomcat</artifactId>
         <groupId>org.springframework.boot</groupId>
      </exclusion>
   </exclusions>
</dependency>

<!--引入jetty-->
<dependency>
   <artifactId>spring-boot-starter-jetty</artifactId>
   <groupId>org.springframework.boot</groupId>
</dependency>
```



**Undertow**

引入方式和Jetty同理：

```xml
<!-- 引入web模块 -->
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
   <exclusions>
      <exclusion>
         <artifactId>spring-boot-starter-tomcat</artifactId>
         <groupId>org.springframework.boot</groupId>
      </exclusion>
   </exclusions>
</dependency>

<!--引入其他的Servlet容器-->
<dependency>
   <artifactId>spring-boot-starter-undertow</artifactId>
   <groupId>org.springframework.boot</groupId>
</dependency>
```

### 4）、嵌入式Servlet容器自动配置原理

EmbeddedServletContainerAutoConfiguration，嵌入式的Servlet容器自动配置，它会根据@ConditionalOnClass(xxx.class)来判断有哪个依赖就创建哪个Servlet容器，例如Tomcat需要有@ConditionalOnClass({ Servlet.class, Tomcat.class })依赖；Undertow容器需要有@ConditionalOnClass({ Servlet.class, Undertow.class, SslClientAuthMode.class })依赖，这正如我们上面所讲的，我们配置哪种容器就导入哪种依赖，并将其他依赖移除即可实现容器的切换：

```java
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
@Import(BeanPostProcessorsRegistrar.class)
//导入BeanPostProcessorsRegistrar：Spring注解版讲过，它能给容器中导入一些组件
//导入了EmbeddedServletContainerCustomizerBeanPostProcessor（后置处理器组件）：
//后置处理器：bean初始化前后（创建完对象，还没赋值赋值）执行初始化工作
public class EmbeddedServletContainerAutoConfiguration {
    
    @Configuration
	@ConditionalOnClass({ Servlet.class, Tomcat.class })//判断当前是否引入了Tomcat依赖；
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)//判断当前容器没有用户自己定义EmbeddedServletContainerFactory：嵌入式的Servlet容器工厂。作用：创建嵌入式的Servlet容器
	public static class EmbeddedTomcat {

		@Bean
		public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
			return new TomcatEmbeddedServletContainerFactory();
		}

	}
    
    /**
	 * Nested configuration if Jetty is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Server.class, Loader.class,
			WebAppContext.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedJetty {

		@Bean
		public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
			return new JettyEmbeddedServletContainerFactory();
		}

	}

	/**
	 * Nested configuration if Undertow is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Undertow.class, SslClientAuthMode.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedUndertow {

		@Bean
		public UndertowEmbeddedServletContainerFactory undertowEmbeddedServletContainerFactory() {
			return new UndertowEmbeddedServletContainerFactory();
		}

	}
```

1）、EmbeddedServletContainerFactory（嵌入式Servlet容器工厂）

```java
public interface EmbeddedServletContainerFactory {

   //获取嵌入式的Servlet容器
   EmbeddedServletContainer getEmbeddedServletContainer(
         ServletContextInitializer... initializers);

}
```

![](images/搜狗截图20180302144835.png)

2）、EmbeddedServletContainer：（嵌入式的Servlet容器）

![](images/搜狗截图20180302144910.png)



3）、以**TomcatEmbeddedServletContainerFactory**（tomcat容器工厂）为例：

```java
@Override
public EmbeddedServletContainer getEmbeddedServletContainer(
      ServletContextInitializer... initializers) {
    //创建一个Tomcat对象
   Tomcat tomcat = new Tomcat();
    
    //配置Tomcat的基本环境
   File baseDir = (this.baseDirectory != null ? this.baseDirectory
         : createTempDir("tomcat"));
   tomcat.setBaseDir(baseDir.getAbsolutePath());
   Connector connector = new Connector(this.protocol);
   tomcat.getService().addConnector(connector);
   customizeConnector(connector);
   tomcat.setConnector(connector);
   tomcat.getHost().setAutoDeploy(false);
   configureEngine(tomcat.getEngine());
   for (Connector additionalConnector : this.additionalTomcatConnectors) {
      tomcat.getService().addConnector(additionalConnector);
   }
   prepareContext(tomcat.getHost(), initializers);
    
    //将配置好的Tomcat传入进去，返回一个EmbeddedServletContainer
    //获取到返回值也就是获取到了Servlet容器，此时会启动Tomcat服务器
    //为什么获取到Servlet容器就会启动Tomcat服务器？请看下面启动原理的讲解
   return getTomcatEmbeddedServletContainer(tomcat);
}
```

4）、我们对嵌入式容器的配置修改是怎么生效？

我们前边的讲解过，有两种方式可以修改：ServerProperties、EmbeddedServletContainerCustomizer。并且我们说过ServerProperties的底层实现就是通过EmbeddedServletContainerCustomizer。那么我们就可以肯定了，一定是**EmbeddedServletContainerCustomizer**帮我们修改了Servlet容器的配置，那它究竟是怎么修改的呢？那我们就要分析一下在**EmbeddedServletContainerAutoConfiguration**自动配置类中导入的一个组件了：**@Import(BeanPostProcessorsRegistrar.class)**，它实现了**ImportBeanDefinitionRegistrar**。我们在spring注解开发中讲过这个接口，实现了该接口的类可以通过它里面的**registerBeanDefinitions()**方法为容器注册一些组件，如图：

![1525439154255](D:\搜狗输入法\搜狗截图\1525439154255.png)

我们着重来说其中的**EmbeddedServletContainerCustomizerBeanPostProcessor**组件。

5）、容器中导入了**EmbeddedServletContainerCustomizerBeanPostProcessor**：

```java
// 重写后置处理器BeanPostProcessor的postProcessBeforeInitialization方法，在对象创建之前进行初始化
// 该重写的方法只干了两件事：
// 1.判断传进来的bean是否是ConfigurableEmbeddedServletContainer类型的。
// 2.如果是该类型调用void postProcessBeforeInitialization()方法进行业务处理
@Override
public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    //如果当前初始化的是一个ConfigurableEmbeddedServletContainer类型的组件
   if (bean instanceof ConfigurableEmbeddedServletContainer) {
       //
      postProcessBeforeInitialization((ConfigurableEmbeddedServletContainer) bean);
   }
   return bean;
}

private void postProcessBeforeInitialization(
			ConfigurableEmbeddedServletContainer bean) {
    //获取所有的定制器，调用每一个定制器的customize方法来给Servlet容器进行属性赋值；
    for (EmbeddedServletContainerCustomizer customizer : getCustomizers()) {
        customizer.customize(bean);
    }
}

private Collection<EmbeddedServletContainerCustomizer> getCustomizers() {
    if (this.customizers == null) {
        // Look up does not include the parent context
        this.customizers = new ArrayList<EmbeddedServletContainerCustomizer>(
            this.beanFactory
            //从容器中获取所有这个类型的组件：EmbeddedServletContainerCustomizer
            //正因为如此，所以如果我们想要定制Servlet容器，正如我们之前的做法一样
            //应该给容器中可以添加一个EmbeddedServletContainerCustomizer类型的组件
            .getBeansOfType(EmbeddedServletContainerCustomizer.class,
                            false, false)
            .values());
        Collections.sort(this.customizers, AnnotationAwareOrderComparator.INSTANCE);
        this.customizers = Collections.unmodifiableList(this.customizers);
    }
    return this.customizers;
}
```

**我们前边说过了，通过配置文件更改Servlet的配置其实就是修改ServerProperties中的属性，ServerProperties其实也是一个定制器，因为它实现了EmbeddedServletContainerCustomizer接口。**



**总结Servlet容器自动配置的步骤：**

1）、SpringBoot根据导入的依赖情况，给容器中添加相应的嵌入式Servlet容器工厂EmbeddedServletContainerFactory（例如TomcatEmbeddedServletContainerFactory）

2）、一旦容器中注册了某个嵌入式Servlet容器工厂的组件，那么该组件要创建对象就会惊动所有的后置处理器，由于Servlet容器自动配置类在容器中注册了EmbeddedServletContainerCustomizerBeanPostProcessor这个后置处理器，所以Servlet容器工厂的组件在创建对象前就会被该后置器拦截处理，具体处理逻辑就是判断有没有哪个Bean是ConfigurableEmbeddedServletContainer类型的，spring boot中支持的三种容器工厂都是该类型的，如图：

![1525440846863](D:\搜狗输入法\搜狗截图\1525440846863.png)



3）、当后置处理拦截到ConfigurableEmbeddedServletContainer类型的容器工厂后，后置处理器便会从容器中获取所有的**EmbeddedServletContainerCustomizer**，调用定制器的定制方法，由于此时还是在创建Servlet容器工厂之前，因此当拿到这些定制方法中的配置后，工厂根据这些配置来创建Servlet容器。



###5）、嵌入式Servlet容器启动原理

首先我们提出两个问题：什么时候创建嵌入式的Servlet容器工厂？什么时候获取嵌入式的Servlet容器并启动Tomcat？

获取嵌入式的Servlet容器工厂的过程：

1）、SpringBoot应用启动运行run方法

2）、通过调用refreshContext(context)方法刷新IOC容器（创建IOC容器对象，并初始化容器，创建容器中的每一个组件），如果是web应用创建**AnnotationConfigEmbeddedWebApplicationContext**，否则：**AnnotationConfigApplicationContext**

3）、调用refresh(context)方法**刷新刚才创建好的ioc容器：**

```java
public void refresh() throws BeansException, IllegalStateException {
   synchronized (this.startupShutdownMonitor) {
      // Prepare this context for refreshing.
      prepareRefresh();

      // Tell the subclass to refresh the internal bean factory.
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

      // Prepare the bean factory for use in this context.
      prepareBeanFactory(beanFactory);

      try {
         // Allows post-processing of the bean factory in context subclasses.
         postProcessBeanFactory(beanFactory);

         // Invoke factory processors registered as beans in the context.
         invokeBeanFactoryPostProcessors(beanFactory);

         // Register bean processors that intercept bean creation.
         registerBeanPostProcessors(beanFactory);

         // Initialize message source for this context.
         initMessageSource();

         // Initialize event multicaster for this context.
         initApplicationEventMulticaster();

         // Initialize other special beans in specific context subclasses.
         onRefresh();

         // Check for listener beans and register them.
         registerListeners();

         // Instantiate all remaining (non-lazy-init) singletons.
         finishBeanFactoryInitialization(beanFactory);

         // Last step: publish corresponding event.
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         destroyBeans();

         // Reset 'active' flag.
         cancelRefresh(ex);

         // Propagate exception to caller.
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         resetCommonCaches();
      }
   }
}
```

4）、 web的ioc容器重写AbstractApplicationContext中定义的onRefresh方法。

5）、web的ioc容器在onRefresh方法的实现逻辑里会通过调用**createEmbeddedServletContainer**()方法创建嵌入式的Servlet容器。

**6）、创建嵌入式的Servlet容器的实现过程：**

​	**① 获取嵌入式的Servlet容器工厂：**

EmbeddedServletContainerFactory containerFactory = getEmbeddedServletContainerFactory();

​	**② 从ioc容器中获取EmbeddedServletContainerFactory 组件：**该组件就是由嵌入式Servlet容器的自动配置类向容器中注册的。

​	**③ 创建TomcatEmbeddedServletContainerFactory容器工厂对象**，然后就是上边讲过的了。后置处理器一看是这个对象，就获取所有的定制器来先定制Servlet容器的相关配置。

​	**④ 使用容器工厂获取嵌入式的Servlet容器**：this.embeddedServletContainer = containerFactory      .getEmbeddedServletContainer(getSelfInitializer());

​	**⑤ 嵌入式的Servlet容器创建对象并启动Servlet容器**



​	至此，Servlet容器创建完毕，意味着整个onRefresh()方法执行完成，但是此时refresh()并没有执行完成（onRefresh只是refresh中的一个方法，后边还有registerListeners等方法需要执行），也就意味着Ioc容器还没创建完，如图：

![1525454526901](D:\搜狗输入法\搜狗截图\1525454526901.png)

**因此我们说，spring boot是先启动嵌入式的Servlet容器，再将ioc容器中剩下没有创建出的对象创建出来。用一句话总结的话就是：在IOC容器启动的过程中，会创建嵌入式的Servlet容器**



## 9、使用外置的Servlet容器

嵌入式Servlet容器：应用打成可执行的jar

​		优点：简单、便携

​		缺点：默认不支持JSP、优化定制比较复杂（使用定制器【通过配置文件修改ServerProperties的属性或自定义EmbeddedServletContainerCustomizer】；自己编写嵌入式Servlet容器的创建工厂【EmbeddedServletContainerFactory】）



外置的Servlet容器：外面安装Tomcat---应用war包的方式打包

### 1）、使用步骤

1）、必须创建一个war项目；（利用idea创建好目录结构），如图：

![1525455504478](D:\搜狗输入法\搜狗截图\1525455504478.png)

我们发现此时创建的工程目录是没有webapp文件夹的，如图：

![1525456012219](D:\搜狗输入法\搜狗截图\1525456012219.png)

我们需要生成webapp文件夹，有两种办法，第一种是自己编写，第二种是让IDEA帮我们生成：

![1525456138949](D:\搜狗输入法\搜狗截图\1525456138949.png)



![1525456399404](D:\搜狗输入法\搜狗截图\1525456399404.png)



点加号来创建web.xml，如图:

![1525456458081](D:\搜狗输入法\搜狗截图\1525456458081.png)

然后会有一个弹窗，我们要在这里修改一下web.xml的生成路径，如图：

![1525456709250](D:\搜狗输入法\搜狗截图\1525456709250.png)





2）、将嵌入式的Tomcat指定为provided，使用IDEA创建springboot的war项目将会自动进行指定，如果我们我们自己搭建一定要记得。

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-tomcat</artifactId>
   <!-- provided:项目打包的时候不会带上这个依赖 --> 
   <scope>provided</scope>
</dependency>
```

3）、必须编写一个**SpringBootServletInitializer**的子类（否则不能启动），并覆写configure方法。如果使用IDEA快速创建springboot的war项目的话，这个子类会自动为我们创建好：

```java
public class ServletInitializer extends SpringBootServletInitializer {

   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      // 传入SpringBoot应用的主程序
      return application.sources(SpringBoot04WebJspApplication.class);
   }

}
```

4）、启动服务器就可以使用。



### 2）、外部Servlet容器启动springboot应用的原理

jar包：首先执行SpringBoot主类的main方法，然后在创建ioc容器的过程中创建嵌入式的Servlet容器。

war包：首先启动服务器，**然后由服务器启动SpringBoot应用（通过SpringBootServletInitializer）**，最后ioc容器。



servlet3.0 中有一项规范（Spring注解版讲解过）：

可以参看文档中servlet-3_1-final.pfg-->**8.2.4 Shared libraries / runtimes pluggability：**

这章中主要是定义了一个规则：

​	1）、服务器启动（web应用启动）会创建当前web应用里面每一个jar包里面ServletContainerInitializer实例

​	2）、ServletContainerInitializer的实现放在jar包的META-INF/services文件夹下，有一个名为javax.servlet.ServletContainerInitializer的文件，内容就是ServletContainerInitializer的实现类的全类名

​	3）、还可以使用@HandlesTypes，在应用启动的时候加载我们感兴趣的类；



流程：

1）、启动Tomcat

2）、org\springframework\spring-web\4.3.14.RELEASE\spring-web-4.3.14.RELEASE.jar!\META-INF\services\javax.servlet.ServletContainerInitializer，如图：

![1525459687926](D:\搜狗输入法\搜狗截图\1525459687926.png)

Spring的web模块里面有这个文件：**org.springframework.web.SpringServletContainerInitializer**，如图：

![1525459794874](D:\搜狗输入法\搜狗截图\1525459794874.png)



3）、SpringServletContainerInitializer将@HandlesTypes(WebApplicationInitializer.class)标注的所有这个类型的后代都传入到onStartup方法的Set<Class<?>>，为这些WebApplicationInitializer类型的后代创建实例。

4）、每一个WebApplicationInitializer的后代都调用自己的onStartup；

![](images/搜狗截图20180302221835.png)

5）、相当于我们的SpringBootServletInitializer的类会被创建对象，并执行onStartup方法

6）、SpringBootServletInitializer实例执行onStartup的时候会调用createRootApplicationContext()方法创建容器：

```java
protected WebApplicationContext createRootApplicationContext(
      ServletContext servletContext) {
    //1、创建SpringApplicationBuilder
   SpringApplicationBuilder builder = createSpringApplicationBuilder();
   StandardServletEnvironment environment = new StandardServletEnvironment();
   environment.initPropertySources(servletContext, null);
   builder.environment(environment);
   builder.main(getClass());
   ApplicationContext parent = getExistingRootWebApplicationContext(servletContext);
   if (parent != null) {
      this.logger.info("Root context already created (using as parent).");
      servletContext.setAttribute(
            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, null);
      builder.initializers(new ParentContextApplicationContextInitializer(parent));
   }
   builder.initializers(
         new ServletContextApplicationContextInitializer(servletContext));
   builder.contextClass(AnnotationConfigEmbeddedWebApplicationContext.class);
    
    //调用configure方法，子类重写了这个方法，将SpringBoot的主程序类传入了进来
   builder = configure(builder);
    
    //使用builder创建一个Spring应用
   SpringApplication application = builder.build();
   if (application.getSources().isEmpty() && AnnotationUtils
         .findAnnotation(getClass(), Configuration.class) != null) {
      application.getSources().add(getClass());
   }
   Assert.state(!application.getSources().isEmpty(),
         "No SpringApplication sources have been defined. Either override the "
               + "configure method or add an @Configuration annotation");
   // Ensure error pages are registered
   if (this.registerErrorPageFilter) {
      application.getSources().add(ErrorPageFilterConfiguration.class);
   }
    //启动Spring应用
   return run(application);
}
```

7）、Spring的应用就启动并且创建IOC容器

```java
public ConfigurableApplicationContext run(String... args) {
   StopWatch stopWatch = new StopWatch();
   stopWatch.start();
   ConfigurableApplicationContext context = null;
   FailureAnalyzers analyzers = null;
   configureHeadlessProperty();
   SpringApplicationRunListeners listeners = getRunListeners(args);
   listeners.starting();
   try {
      ApplicationArguments applicationArguments = new DefaultApplicationArguments(
            args);
      ConfigurableEnvironment environment = prepareEnvironment(listeners,
            applicationArguments);
      Banner printedBanner = printBanner(environment);
      context = createApplicationContext();
      analyzers = new FailureAnalyzers(context);
      prepareContext(context, environment, listeners, applicationArguments,
            printedBanner);
       
       //刷新IOC容器
      refreshContext(context);
      afterRefresh(context, applicationArguments);
      listeners.finished(context, null);
      stopWatch.stop();
      if (this.logStartupInfo) {
         new StartupInfoLogger(this.mainApplicationClass)
               .logStarted(getApplicationLog(), stopWatch);
      }
      return context;
   }
   catch (Throwable ex) {
      handleRunFailure(context, listeners, analyzers, ex);
      throw new IllegalStateException(ex);
   }
}
```

**总结一句话：使用外置的Servlet容器是先启动Servlet容器，再启动SpringBoot应用**



# 五、Docker

## 1、简介

**Docker**是一个开源的应用容器引擎；是一个轻量级容器技术；

Docker支持将软件编译成一个镜像；然后在镜像中各种软件做好配置，将镜像发布出去，其他使用者可以直接使用这个镜像；

运行中的这个镜像称为容器，容器启动是非常快速的。

![](images/搜狗截图20180303145450.png)



![](images/搜狗截图20180303145531.png)

## 2、核心概念

docker主机(Host)：安装了Docker程序的机器（Docker直接安装在操作系统之上）

docker客户端(Client)：连接docker主机进行操作

docker仓库(Registry)：用来保存各种打包好的软件镜像

docker镜像(Images)：软件打包好的镜像，放在docker仓库中

docker容器(Container)：镜像启动后的实例称为一个容器，容器是独立运行的一个或一组应用

![](images/搜狗截图20180303165113.png)

**使用Docker的步骤：**

1）、安装Docker

2）、去Docker仓库找到这个软件对应的镜像

3）、使用Docker运行这个镜像，这个镜像就会生成一个Docker容器

4）、对容器的启动停止就是对软件的启动停止

## 3、安装Docker

#### 1）、安装linux虚拟机

​	1）、VMWare、VirtualBox（安装）

​	2）、导入虚拟机文件centos7-atguigu.ova到VirtualBox

​	**注：我没使用这个文件，我自己装了一个新的。**

​	3）、双击启动linux虚拟机;使用  root/ 289443登陆

​	4）、如果使用VirtualBox要设置虚拟机的网卡：

![1525508929010](D:\搜狗输入法\搜狗截图\1525508929010.png)

​	5）、设置好网络后，使用命令重启虚拟机网络：

```shell
service network restart
```

​	6）、关闭防火墙：

```shell
# 查看防火墙状态
service firewalld status
# 关闭防火墙
service firewalld stop
```

​	7）、使用(Xshell)客户端连接linux服务器进行命令操作，有时候虚拟机重启后网络没有初始化好，我们使用命令**service network restart**重启以下网络配置即可。



#### 2）、在linux虚拟机上安装docker

步骤：

![1525498051976](D:\搜狗输入法\搜狗截图\1525498051976.png)



```shell
# 1.检查内核版本，必须是3.10及以上
uname -r
# 2.安装docker
yum install docker
# 3.输入y确认安装
# 4.启动docker
[root@localhost ~]# systemctl start docker
[root@localhost ~]# docker -v
Docker version 1.12.6, build 3e8e77d/1.12.6
# 5.开机启动docker
[root@localhost ~]# systemctl enable docker
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
# 6.停止docker
systemctl stop docker
```



**我启动Docker的时候一直报错，说什么docker.sock还有group什么的，找了一下午的解决方法，终于找到了：**

**[docker启动失败解决方法](https://blog.csdn.net/hongwei15732623364/article/details/80069068)**



## 4、Docker常用命令&操作

### 1）、镜像操作

| 操作 | 命令                                            | 说明                                                     |
| ---- | ----------------------------------------------- | -------------------------------------------------------- |
| 检索 | docker  search 关键字  eg：docker  search redis | 我们经常去docker  hub上检索镜像的详细信息，如镜像的TAG。 |
| 拉取 | docker pull 镜像名:tag                          | :tag是可选的，tag表示标签，多为软件的版本，默认是latest  |
| 列表 | docker images                                   | 查看所有本地镜像                                         |
| 删除 | docker rmi ImageId                              | 删除指定的本地镜像                                       |

https://hub.docker.com/

### 2）、容器操作

软件镜像（QQ安装程序）----运行镜像----产生一个容器（正在运行的软件，运行的QQ）；

步骤：

````shell
1、搜索镜像
[root@localhost ~]# docker search tomcat
2、拉取镜像
[root@localhost ~]# docker pull tomcat
3、根据镜像运行容器
docker run --name mytomcat -d tomcat:latest
4、docker ps  
查看运行中的容器
5、 停止运行中的容器
docker stop  容器的id
6、查看所有的容器
docker ps -a
7、启动容器
docker start 容器id
8、删除一个容器
 docker rm 容器id
9、启动一个做了端口映射的tomcat
[root@localhost ~]# docker run -d -p 8888:8080 tomcat
-d：后台运行
-p: 将主机（这里是虚拟机）的端口映射到容器的一个端口    格式-->主机端口:容器内部的端口

10、为了演示简单关闭了linux的防火墙
service firewalld status ；查看防火墙状态
service firewalld stop：关闭防火墙
11、查看容器的日志
docker logs container-name/container-id

更多命令参看
https://docs.docker.com/engine/reference/commandline/docker/
拉取镜像时，一般官方的镜像也会在描述文档中做详细说明，可以参考每一个镜像的文档

````

**我在docker run运行容器的时候，会报错：**

```shell
/usr/bin/docker-current: Error response from daemon: shim error: docker-runc not installed on system.
```

**解决方法（可以参看[docker run报错解决方案](https://www.cnblogs.com/cxbhakim/p/8862758.html)）：**

```shell
cd /usr/libexec/docker/
ln -s docker-runc-current docker-runc 
```



另外，我在通过-p 端口映射后启动tomcat后一致报一个错：

```shel
/usr/bin/docker-current: Error response from daemon: driver failed programming external connectivity on endpoint myTomcat (a65ab63edd429294cf29a30cf6150364eedaf88e1fb661f264bed622134f5378): exec: "docker-proxy": executable file not found in $PATH.
```

这个错误的原因是没有在$PATH路径下找到docker-proxy，这里说明一下PATH，我们先来看PATH在哪：

```shell
[root@localhost bin]# echo $PATH
# 这些路径都是PATH，我们随便用哪个都行，这里我用的是/usr/bin
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin
```

然后我们在docker的目录下找到该文件：

![1525526498474](D:\搜狗输入法\搜狗截图\1525526498474.png)

其实docker在PATH下要找的就是/usr/libexec/docker/docker-proxy-current这个文件，因此我们把这个文件复制到PATH目录下，然后改名为"docker-proxy"即可。



### 3）、安装MySQL示例

```shell
docker pull mysql
```



演示错误的启动：

```shell
[root@localhost ~]# docker run --name mysql01 -d mysql
42f09819908bb72dd99ae19e792e0a5d03c48638421fa64cce5f8ba0f40f5846

# STATUS=Exited (1)  说明mysql退出了
[root@localhost ~]# docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                           PORTS               NAMES
42f09819908b        mysql               "docker-entrypoint.sh"   34 seconds ago      Exited (1) 33 seconds ago                            mysql01
538bde63e500        tomcat              "catalina.sh run"        About an hour ago   Exited (143) About an hour ago                       compassionate_
goldstine
c4f1ac60b3fc        tomcat              "catalina.sh run"        About an hour ago   Exited (143) About an hour ago                       lonely_fermi
81ec743a5271        tomcat              "catalina.sh run"        About an hour ago   Exited (143) About an hour ago                       sick_ramanujan


# 查看异常日志
[root@localhost ~]# docker logs 42f09819908b
error: database is uninitialized and password option is not specified 
  You need to specify one of MYSQL_ROOT_PASSWORD, MYSQL_ALLOW_EMPTY_PASSWORD and MYSQL_RANDOM_ROOT_PASSWORD
# 意思是上面给出的那三个参数必须指定一个
```



正确的启动方式（但是未做端口映射），可参看docker search-->mysql官方版中给出的文档：

```shell
[root@localhost ~]# docker run --name mysql01 -e MYSQL_ROOT_PASSWORD=123456 -d mysql
b874c56bec49fb43024b3805ab51e9097da779f2f572c22c695305dedd684c5f
[root@localhost ~]# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
b874c56bec49        mysql               "docker-entrypoint.sh"   4 seconds ago       Up 3 seconds        3306/tcp            mysql01
```

做了端口映射的正确启动方式：

```shell
[root@localhost ~]# docker run -p 3306:3306 --name mysql02 -e MYSQL_ROOT_PASSWORD=123456 -d mysql
ad10e4bc5c6a0f61cbad43898de71d366117d120e39db651844c0e73863b9434
[root@localhost ~]# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
ad10e4bc5c6a        mysql               "docker-entrypoint.sh"   4 seconds ago       Up 2 seconds        0.0.0.0:3306->3306/tcp   mysql02
```

mysql的docker容器几个其他的高级操作

```shell
#-v参数是把主机的/conf/mysql（随便创建什么名都行，这个路径只是演示才这么写）文件夹挂载到
# mysql的docker容器的/etc/mysql/conf.d文件夹里面
docker run --name mysql03 -v /conf/mysql:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

# 以后如果我们需要改mysql的配置文件就只需要把mysql配置文件放在自定义的文件夹下（/conf/mysql）
# 它会将我们的配置文件和默认的配置文件进行合并

# 除上边使用配置文件的形式外，我们还可以通过指定参数而不使用配置文件的形式来启动
# --character-set...这一堆是指定mysql的一些配置参数
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

```



# 六、SpringBoot与数据访问

## 1、JDBC

首先创建一个新的项目，选中Mysql、Jdbc、Web这三个模块：

![1525539366135](D:\搜狗输入法\搜狗截图\1525539366135.png)



该项目的pom文件中的starter有下面这些：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```



在配置文件中配置数据源：

```yaml
spring.datasource.username=root
spring.datasource.password=289443
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/jdbc
```

效果：

​	默认是用org.apache.tomcat.jdbc.pool.DataSource作为数据源。

​	数据源的相关配置都在DataSourceProperties里面。



数据源的自动配置原理：

在org.springframework.boot.autoconfigure.jdbc包下，如图：

![1525540679637](D:\搜狗输入法\搜狗截图\1525540679637.png)

1、参考DataSourceConfiguration，根据配置创建数据源，默认使用Tomcat连接池。可以使用spring.datasource.type指定自定义的数据源类型：

![1525540919966](D:\搜狗输入法\搜狗截图\1525540919966.png)

2、SpringBoot默认支持的数据源：

（1）org.apache.tomcat.jdbc.pool.DataSource

（2）com.zaxxer.hikari.HikariDataSource

（3）org.apache.commons.dbcp.BasicDataSource

（4）org.apache.commons.dbcp2.BasicDataSource

（5）自定义的数据源



3、自定义数据源类型

```java
/**
 * Generic DataSource configuration.
 */
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type")
static class Generic {

   @Bean
   public DataSource dataSource(DataSourceProperties properties) {
       //使用DataSourceBuilder创建数据源，利用反射创建响应type的数据源，并且绑定相关属性
      return properties.initializeDataSourceBuilder().build();
   }

}
```

4、在**DataSourceAutoConfiguration**这个自动配置类中给容器添加了一个组件（74行）：

**DataSourceInitializer （实现了ApplicationListener接口）：**；

​	作用：

​		1）、通过runSchemaScripts()方法运行建表语句；

​		2）、通过runDataScripts()方法运行插入数据的sql语句；



想使用这个功能的话有两种方式：

（1）按照spring boot的默认规则将建表的.sql文件命名为schema.sql（或schema-all.sql）,并且将文件放在classpath下：

![1525542362150](D:\搜狗输入法\搜狗截图\1525542362150.png)

然后运行spring boot，会发现它在数据库中为我们创建了表：

![1525542411390](D:\搜狗输入法\搜狗截图\1525542411390.png)



（2）我们不使用spring boot默认的规则，而是自己任意定义文件名和路径，然后在配置文件中进行配置：

![1525542546748](D:\搜狗输入法\搜狗截图\1525542546748.png)

这是建表的文件，同理插入数据的.sql文件也可以通过spring.datasource.data=xxx,xxx来进行配置。

**注意：每次启动spring boot都会为我们重新建表，因此之前表中的数据会丢失！因此我们建完表应该把这个文件删掉！**



5、操作数据库：自动配置了JdbcTemplate操作数据库，参看JdbcTemplateAutoConfiguration这个自动配置类。



## 2、整合Druid数据源

1.在pom文件中导入druid连接池的依赖

```xml
<!-- 导入druid数据源 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.8</version>
</dependency>
```

2.在spring boot的配置文件中指定数据源为druid：

```properties
# 将数据源指定为druid
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
```

3.配置druid数据源的其他属性以及监控相关的配置：

```properties
# 数据源其他配置
spring.datasource.initialSize=5
spring.datasource.minIdle=5
spring.datasource.maxActive=20
spring.datasource.maxWait=60000
spring.datasource.timeBetweenEvictionRunsMillis=60000
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=SELECT 1 FROM DUAL
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false
spring.datasource.poolPreparedStatements=true

# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
spring.datasource.filters=stat,wall,log4j
spring.datasouce.maxPoolPreparedStatementPerConnectionSize=20
spring.datasouce.useGlobalDataSourceStat=true
spring.datasouce.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

**在这里要注意一个事情，我们看我们的配置文件中配置的这些属性，IDEA编辑器给出了警告，如图：**

![1525583167454](D:\搜狗输入法\搜狗截图\1525583167454.png)

这是由于我们这样配置的话其实最终是修改DataSourceProperties.java这个类的属性，但是我们配置的这些属性在DataSourceProperties中是没有的，我们知道我们现在使用的druid数据源，这些属性是druid数据源中才有的，因此我们现在要把这些配置映射到com.alibaba.druid.pool.DruidDataSource这个类中去，所以这里我们要自己写一个配置类，并给容器中添加DruidDataSource这个组件，并且模仿spring boot配置文件映射属性的方法，在类上标注@ConfigurationProperties(prefix = "spring.datasource")以便让我们在配置文件中以"spring.datasource"开头的配置能映射到我们自己添加的这个DruidDataSource组件中去：

```java
/**
 * description: Druid数据源的配置类，让配置文件中配置的数据源相关的配置能映射到DruidDataSource中去。
 * 使用@ConfigurationProperties(prefix = "spring.datasource")注解就是为了让配置文件中
 * 以"spring.datasource"开头的配置能映射到我们给容器中添加的DruidDataSource这个组件中去。
 * @author 任伟
 * @date 2018/5/6 13:12
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
       return  new DruidDataSource();
    }
}
```



4.配置Druid的监控，需要配置一个Servlet和Filter：

```java
@Configuration
public class DruidConfig {
    
   /**
	* 配置Druid的监控,需要配置一个管理后台的Servlet（即StatViewServlet）和一个web监控的filter（即
	* WebStatFilter）。我们配置这个StatViewServlet的时候，还能给它配置一些属性例如登录的账号密码等，
	* 这些属性都可以在StatViewServlet中及其父类ResourceServlet中找到。
 	*/
	@Bean
    public ServletRegistrationBean statViewServlet(){
        
        // 传入要注册在容器中的Servlet，并且传入要处理哪些URL的请求
        ServletRegistrationBean servletBean = 
            new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "289443");
        // 配置允许谁访问，如果不配或者配置为空，就是允许所有
        initParams.put("allow", "localhost");
        // 配置拒绝谁访问，这里配置为本机的IP
        initParams.put("deny", "192.168.1.4");
        servletBean.setInitParameters(initParams);
        return servletBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new WebStatFilter());
        // 配置filter要拦截的请求,这里拦截所有请求
        filterBean.setUrlPatterns(Arrays.asList("/*"));

        // 配置Filter的初始化参数，要配置的初始化参数都能在WebStatFilter中找到
        Map<String, String> initParams = new HashMap<>();
        // 配置要放行的请求，这里放行静态资源和"/druid/"下的所有请求
        initParams.put("exclusions", "*.js,*.css,/druid/");
        filterBean.setInitParameters(initParams);

        return filterBean;
    }
}
```





## 3、整合MyBatis

### 1）、整合mybatis的步骤：

**1. 创建新项目，starter选中web、mysql、jdbc、mybatis，生成的项目中pom文件有以下依赖：**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- mybatis的starter是由mybatis官方编写的 -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```



**mybatis的starter的依赖关系图如下：**

![](images/搜狗截图20180305194443.png)



**2. 我们将默认的tomcat数据源替换为druid，步骤省略，参考第2节，唯一不同的是数据库不再连接本地的jdbc库而是连本地mybatis库。**

**3. 给数据库建表，这里我们还是使用之前讲的通过.sql文件建表：**

![1525590038401](D:\搜狗输入法\搜狗截图\1525590038401.png)

**注意：建好表之后要将.sql建表文件删除掉，或者把配置文件中的建表配置注释掉，以免下次运行时重新建表，将表中的数据全都清空了。建议是将建表文件删了，因为万一有人将注释打开了，那数据就全没了。**

**4. 创建数据库表对应的JavaBean：**

![1525590197245](D:\搜狗输入法\搜狗截图\1525590197245.png)



### 2）、mybatis的使用方式



#### 	1. 注解版

使用注解版，无需使用xxxMapper.xml来编写sql语句，直接将sql写在xxxMapper的方法上，并使用增、删、改、查注解标注即可，如下：

```java
/**
 * description: 注解版的方式使用mybatis。
 * 注解版方式无需xxxMapper.xml编写sql语句，而是使用@Select、@Delete、@Update、@Insert注解编写sql。
 *
 * @author 任伟
 * @date 2018/5/6 15:15
 */
@Mapper // 使用@Mapper注解指定这是一个操作数据库的mapper
public interface DepartmentMapper {

    /**
     * 如果是表中的主键是自增主键，那么不做任何操作的情况下我们是无法获取新插入的那条数据的主键。
     * 使用@Options注解可以在插入完成后将新增的数据的主键给我们封装进插入操作时传入的javaBean中。
     * 其中@Options中的useGeneratedKeys属性默认值是false，即不不使用主键自生成，我们需要更改为true，
     * 另外，它的keyProperty属性是指定表中的主键的属性名，默认是"id"，如果我们表中的主键命名是"id"则
     * 可以省略该属性，否则要将主键的属性名进行配置。
     */
    @Options(useGeneratedKeys=true, keyProperty = "id")
    @Insert(" insert into department(departmentName) values(#{departmentName}) ")
    int insertDept(Department department);

    @Delete(" delete from department where id = #{id} ")
    int deleteDept(Integer id);

    @Update(" update department set departmentName = #{departmentName} where id = #{id} ")
    int updateDept(Department department);

    @Select(" select * from department where id = #{id} ")
    Department selectDeptById(Integer id);
}
```



**上面的步骤看似都很好，但是我们要考虑一个问题，我们以前将spring和mybatis进行整合的时候，会有一个mybatis的配置文件，里边可以配置mapper扫描、缓存、别名等等，而我们使用spring boot整合的时候没有进行任何配置，这样会不会默认的配置有问题呢？我们该如何对mybatis的配置进行定制呢？**

下面我们先来通过一个问题来演示使用自动配置产生的一个问题，我先将department表中departmentName这个字段名改为department_name，如图：

![1525593674676](D:\搜狗输入法\搜狗截图\1525593674676.png)

然后将Mapper中的sql语句也改成和数据库表一致的字段名，如图：

![1525593867109](D:\搜狗输入法\搜狗截图\1525593867109.png)

然后我们通过浏览器发送查询请求，来查一条数据，如图：

![1525593943631](D:\搜狗输入法\搜狗截图\1525593943631.png)

我们发现查出的departmentName为null，这是由于从数据库中查出的数据的字段名为"department_name"，而我们javaBean中的字段名为"departmentName"，从而无法进行映射，如果是我们之前通过配置文件的形式配置mybatis的话，只要开启驼峰命名就可以轻松解决这个问题，但是在Springboot中要如何解决呢？

其实也很简单，我们之前学过定制器的原理，我们只要使用mybatis的定制器来自定义mybatis的配置即可。

**定制MyBatis的配置规则，给容器中添加一个ConfigurationCustomizer：**

```java
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
```

然后我们再次查询，发现就可以查出结果了：

![1525594693003](D:\搜狗输入法\搜狗截图\1525594693003.png)



另外，再扩展一个知识点，我们编写的Mapper的类上都要通过标注@Mapper注解来标识这是一个Mapper，这样它才能加入到容器中以便让Service进行依赖注入，如果我们的Mapper非常多，也能使用@MapperScan来开启Mapper扫描，@MapperScan只要标注在配置类上就行，springboot的主配置类或mabatis的配置类都行：

```java
// 使用@MapperScan注解来批量扫描所有的Mapper接口
@MapperScan(basePackages = "com.atguigu.springboot.mapper")
@SpringBootApplication
public class SpringBoot06DataMybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot06DataMybatisApplication.class, args);
	}
}
```



#### 2. 配置文件版

EmployeeMapper：

```java
@Mapper
public interface EmployeeMapper {
    Employee getEmpById(Integer id);
}
```

EmployeeController:

```java
@RestController
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }
}
```

mybatis的全局配置文件 mybatis-config.xml：

```xml
<!-- mybatis的全局配置文件-->
<configuration>
    <settings>
        <!--使用驼峰命名规则-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```

mapper的sql映射文件 EmployeeMapper.xml：

```xml
<!--mapper的sql映射文件-->
<mapper namespace="cn.rain.mapper.EmployeeMapper">
    <select id="getEmpById" parameterType="java.lang.Integer" resultType="cn.rain.bean.Employee">
        SELECT * FROM employee WHERE id=#{id}
    </select>
</mapper>
```

**在spring boot的全局配置文件中配置mybatis的全局配置文件及sql映射文件的路径：**

```properties
# 配置mybatis全局配置文件的路径
mybatis.config-location=classpath:mybatis/mybatis-config.xml
# 配置mapper的sql映射文件 xxxMapper.xml 的路径
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```

最终效果，如图：

![1525597094596](D:\搜狗输入法\搜狗截图\1525597094596.png)

**更多使用方式参照官方文档：[mybatis官方文档](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)**





## 4、整合SpringData JPA（该节视频没看）

### 1）、SpringData简介

![](images/搜狗截图20180306105412.png)

### 2）、整合SpringData JPA

JPA:ORM（Object Relational Mapping）；

1）、编写一个实体类（bean）和数据表进行映射，并且配置好映射关系；

```java
//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "tbl_user") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class User {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name = "last_name",length = 50) //这是和数据表对应的一个列
    private String lastName;
    @Column //省略默认列名就是属性名
    private String email;
```

2）、编写一个Dao接口来操作实体类对应的数据表（Repository）

```java
//继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {
}

```

3）、基本的配置JpaProperties

```yaml
spring:  
 jpa:
    hibernate:
#     更新或者创建数据表结构
      ddl-auto: update
#    控制台显示SQL
    show-sql: true
```



# 七、启动配置原理

## 1、spring boot启动流程

我们通过在主配置类的main方法中加上断点，来一步步看springboot的启动流程：

![1525613886282](D:\搜狗输入法\搜狗截图\1525613886282.png)

![1525613922320](D:\搜狗输入法\搜狗截图\1525613922320.png)

![1525613961146](D:\搜狗输入法\搜狗截图\1525613961146.png)

接下来我们就先分析**new SpringApplication(sources)**，再分析**run(args)**方法。

### **1）、创建SpringApplication对象**

下面的代码分两步，先创建SpringApplication(sources)对象，然后运行run(args)方法：

```java
// SpringApplication.java --> 1118行
return new SpringApplication(sources).run(args);
```

这里我们先看它是怎么创建SpringApplication对象，它首先通过调用initialize()方法：

```java
// SpringApplication.java --> 224行
public SpringApplication(Object... sources) {
	initialize(sources);
}
```

我们查看initialize()方法：

```java
// SpringApplication.java --> 244行
private void initialize(Object[] sources) {
    // 保存主配置类到this.sources中，它是一个Set集合
    if (sources != null && sources.length > 0) {
        this.sources.addAll(Arrays.asList(sources));
    }
    // 判断当前是否一个web应用
    this.webEnvironment = deduceWebEnvironment();
    // 从类路径下找到META-INF/spring.factories配置的所有ApplicationContextInitializer然后保存起来
    setInitializers((Collection) getSpringFactoriesInstances(
        ApplicationContextInitializer.class));
    // 从类路径下找到META-INF/spring.factories配置的所有ApplicationListener
    setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    // 遍历所有的配置类，看哪个配置类有main方法哪个就是主配置类主配置类
    this.mainApplicationClass = deduceMainApplicationClass();
}
```

一共在类路径下找到以下6个ApplicationContextInitializer：

![](images/搜狗截图20180306145727.png)

一共在类路径下找到以下10个ApplicationListener：

![](images/搜狗截图20180306145855.png)

至此，SpringApplication对象创建完成，接下来通过调用run()方法运行spring boot应用。



### 2）、运行run方法

```java
// SpringApplication.java --> 285行
public ConfigurableApplicationContext run(String... args) {
   StopWatch stopWatch = new StopWatch();
   stopWatch.start();
   ConfigurableApplicationContext context = null;
   FailureAnalyzers analyzers = null;
   configureHeadlessProperty();
    
   // 获取SpringApplicationRunListeners，从类路径下META-INF/spring.factories获取
   // 注意第1节说的监听器不一样，那个是ApplicationListener；这里是SpringApplicationRunListeners
   SpringApplicationRunListeners listeners = getRunListeners(args);
   // SpringApplicationRunListeners中有5个回调方法
   // 这里回调所有的获取到的SpringApplicationRunListener的starting()方法
   listeners.starting();
   try {
      // 封装命令行参数
      ApplicationArguments applicationArguments = new DefaultApplicationArguments(
            args);
      // 调用prepareEnvironment(listeners, applicationArguments)方法准备环境
      // prepareEnvironment()方法中创建环境完成后，
      // 回调SpringApplicationRunListener.environmentPrepared()，表示环境准备完成
      ConfigurableEnvironment environment = prepareEnvironment(listeners,
            applicationArguments);
      
      // 打印spring boot的banner图标 
      Banner printedBanner = printBanner(environment);
       
      // 创建ApplicationContext，决定创建web环境的ioc容器还是普通的ioc容器
      context = createApplicationContext();
      // 创建异常分析对象，当启动出现异常时最下面的catch块中会使用该对象做异常分析报告 
      analyzers = new FailureAnalyzers(context);
      // 准备上下文环境，其中比较重要的几步是：
      // 1.会将environment保存到ioc中
      // 2.然后调用applyInitializers()方法，该方法会回调之前保存的所有的
      // ApplicationContextInitializer的initialize()方法
      // 3.回调所有的SpringApplicationRunListener的contextPrepared()；
      prepareContext(context, environment, listeners, applicationArguments,
            printedBanner);
      // prepareContext()方法运行完成以后回调所有的SpringApplicationRunListener的contextLoaded()
       
      // 刷新容器：ioc容器初始化（如果是web应用还会创建嵌入式的Tomcat），详见Spring注解版的讲解
      // 刷新容器其实就是扫描、创建、加载所有组件的地方（例如配置类，自定义组件，自动配置类等等）
      refreshContext(context);
      // 从ioc容器中获取所有的ApplicationRunner和CommandLineRunner组件进行回调
      // 先回调ApplicationRunner，再回调CommandLineRunner
      afterRefresh(context, applicationArguments);
      // 所有的SpringApplicationRunListener回调finished()方法
      listeners.finished(context, null);
      stopWatch.stop();
      if (this.logStartupInfo) {
         new StartupInfoLogger(this.mainApplicationClass)
               .logStarted(getApplicationLog(), stopWatch);
      }
      // 整个SpringBoot应用启动完成以后返回启动的ioc容器；
      return context;
   }
   catch (Throwable ex) {
      handleRunFailure(context, listeners, analyzers, ex);
      throw new IllegalStateException(ex);
   }
}
```

至此整个SpringBoot应用启动完成，并且会返回ioc容器，这里对几个重要的点进行总结：

1.我们需要注意启动配置中的几个重要的事件回调机制。

2.以下两个组件需要提前在类路径下的META-INF/spring.factories文件中进行配置，spring boot会在启动过程中到该文件中进行寻找并将它们加载到容器中：

​	**(1) ApplicationContextInitializer**

​	**(2) SpringApplicationRunListener**

3.以下两个组件无需提前在文件中配置，只需要放在ioc容器中即可：

​	**(1) ApplicationRunner**

​	**(2) CommandLineRunner**



## 2、事件监听机制

需要提前配置在META-INF/spring.factories中的组件：

**（1）ApplicationContextInitializer**

```java
public class MyApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer...initialize()...");
    }
}
```

**（2）SpringApplicationRunListener**

```java
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    //必须有的构造器，否则启动会报错
    public MySpringApplicationRunListener(SpringApplication application, String[] args){

    }

    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener...starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        System.out.println("SpringApplicationRunListener...environmentPrepared.."+o);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener...contextLoaded...");
    }

    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("SpringApplicationRunListener...finished...");
    }
}
```



在classpath下的META-INF/spring.factories配置上面这两个组件：

```properties
org.springframework.context.ApplicationContextInitializer=\
com.atguigu.springboot.listener.HelloApplicationContextInitializer

org.springframework.boot.SpringApplicationRunListener=\
com.atguigu.springboot.listener.HelloSpringApplicationRunListener
```

![1525616127284](D:\搜狗输入法\搜狗截图\1525616127284.png)





无需提前配置在META-INF/spring.factories，只需要放在ioc容器中即可的组件

**（1）ApplicationRunner**

```java
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner...run....");
    }
}
```



**（2）CommandLineRunner**

```java
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...run..."+ Arrays.asList(args));
    }
}
```



**最终效果，我们启动spring boot：**

![1525615966899](D:\搜狗输入法\搜狗截图\1525615966899.png)

![1525616022719](D:\搜狗输入法\搜狗截图\1525616022719.png)





# 八、自定义starter

想要自定义starter，我们需要掌握以下几点：

## **1、starter的依赖**

我们自定义的场景需要使用到的依赖是什么？即此场景需要依赖哪些jar包

## **2、编写自动配置**

自动配置类中比较重要的元素列举如下：

```java
@Configuration  //指定这个类是一个配置类
@ConditionalOnXXX  //在指定条件成立的情况下自动配置类生效
@AutoConfigureAfter  //指定自动配置类的顺序
@EnableConfigurationProperties //让xxxProperties生效加入到容器中
@Bean  //给容器中添加组件
public class xxxAutoConfiguration{
    ...
}

@ConfigurationPropertie("spring.xxx") //创建相关xxxProperties类来绑定相关的配置
public class xxxProperties {
	...
}
```

```properties
# 另外需要注意的一点，想让自动配置类要能加载，并且需要自动配置类启动的时候就加载，
# 那么需要配置在classpath:META-INF/spring.factories中，示例如下：
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
```



## 3、starter的设计模式

spring boot的做法是启动器只用来做依赖导入，专门来写一个自动配置模块。然后让启动器依赖自动配置模块，别人只需要引入启动器即可，我们以starter-web为例来说明。

我们看starter-web这个模块中没有任何的java代码，它仅仅有一个pom文件来进行对其他模块的依赖：

![1525625588030](D:\搜狗输入法\搜狗截图\1525625588030.png)

那starter-web这个模块依赖了谁呢？其实就是我们上边说的，它依赖了自动配置模块：

![1525625724499](D:\搜狗输入法\搜狗截图\1525625724499.png)



## 4、starter的命名

spring boot官方定义对starter的命名一般是 spring-boot-starter-xxx；

而非官方的starter一般命名为 xxx-spring-boot-starter，例如 mybatis-spring-boot-starter。

我们建议如果是我们自定义starter的话，使用后者的命名方式，即 xxx-spring-boot-starter。



## 5、自定义starter

### 1）、创建starter模块

由于这次我们是自定义starter，不需要依赖spring boot的任何模块，因此这里我们创建一个maven工程，定义该starter模块的pom文件，让其依赖自动配置模块，如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>cn.rain</groupId>
    <artifactId>wrain-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--自定义的starter-->
    <dependencies>
        <!--引入自定义starter的自动配置模块-->
        <dependency>
            <groupId>cn.rain</groupId>
            <artifactId>wrain-spring-boot-starter-autoconfigurer</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```

### 2）、创建starter的自动配置模块

这里我们使用spring Initailizr创建一个spring boot项目，但是不选择任何的spring boot模块，编辑该模块的pom文件，去除没用的配置，只让其依赖spring-boot-starter，如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.rain</groupId>
    <artifactId>wrain-spring-boot-starter-autoconfigurer</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>wrain-spring-boot-starter-autoconfigurer</name>
    <description>Demo project for Spring Boot</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--引入spring-boot-starter，所有的starter都要引用spring的starter-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```



编写配置文件属性的映射类：

```java
/**
 * description: spring boot启动时首先会读取配置文件以"wrain.hello"开头的配置，
 * 然后映射到该类的属性中。
 *
 * @author 任伟
 * @date 2018/5/7 1:35
 */
@ConfigurationProperties("wrain.hello")
public class WRainProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

```

编写自动配置类：

```java
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
```



编写业务类：

```java
/**
 * description: 简单模拟该starter的业务场景，调用该类的sayHello(String name)方法，
 * 可以返回一个"prefix + name + suffix"的字符串，并且prefix和suffix是可以通过配置文件配置的。
 *
 * @author 任伟
 * @date 2018/5/7 1:33
 */
public class HelloService {
    private WRainProperties wRainProperties;

    public void setwRainProperties(WRainProperties wRainProperties) {
        this.wRainProperties = wRainProperties;
    }

    public String sayHello(String name){
        return wRainProperties.getPrefix()+"-" +name + wRainProperties.getSuffix();
    }
}
```

### 3）、将starter安装到maven仓库

由于starter模块依赖自动配置模块，因此我们先安装自动配置模块：

![1525630777530](D:\搜狗输入法\搜狗截图\1525630777530.png)

再安装starter模块：

![1525630886673](D:\搜狗输入法\搜狗截图\1525630886673.png)

### 4）、测试自定义starter

创建新的spring boot项目，由于我们自定义的启动器在web环境下才能生效，因此我们选中web模块，在该测试项目的pom文件中引入我们自定义starter的坐标，如下：

```xml
<!--引入自定义的starter-->
<dependency>
    <groupId>cn.rain</groupId>
    <artifactId>wrain-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

编写用于测试的Controller：

```java
/**
 * description: 测试自定义的starter
 * @author 任伟
 * @date 2018/5/7 2:33
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(name);
    }
}
```

在配置文件中配置前缀和后缀：

```properties
wrain.hello.prefix="你好，"
wrain.hello.suffix="，我是自定义的starter！"
```

浏览器访问的最终效果：

![1525632157310](D:\搜狗输入法\搜狗截图\1525632157310.png)



# 更多SpringBoot整合示例

https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples













