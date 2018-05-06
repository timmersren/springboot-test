package cn.rain;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
