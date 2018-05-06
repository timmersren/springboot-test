package cn.rain;

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
