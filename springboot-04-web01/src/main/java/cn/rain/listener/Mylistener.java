package cn.rain.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * description:
 *
 * @author 任伟
 * @date 2018/5/4 17:12
 */
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
