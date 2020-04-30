package cn.king.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/2 16:33
 * @version: 1.0.0
 * @description:
 */
@Configuration
public class HystrixDashboardConfig {

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(streamServlet);
        // 这个Servlet的启动顺序
        servletRegistrationBean.setLoadOnStartup(1);
        // 这个是Servlet的映射路径, 自定义
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        // 这个是Servlet的名字, 自定义
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }

}
