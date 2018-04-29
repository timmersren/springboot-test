package cn.rain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03LoggingApplicationTests {

    // 获取日志记录器Logger
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 日志的级别由低到高：trace < debug < info < warn < error。
     * 可以通过调整日志的级别，从而控制仅输出该级别及更高级别的日志。
     * 我们运行此方法发现只打印出了info及以上级别的日志，说明spring boot默认
     * 的日志级别是info（也称为root级别），我们可以通过在全局配置文件中配置来
     * 更改日志级别（可以针对每个包调整级别）。
     */
    @Test
    public void contextLoads() {
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }
}
