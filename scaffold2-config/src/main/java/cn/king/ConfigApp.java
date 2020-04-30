package cn.king;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/5 19:01
 * @version: 1.0.0
 * @description:
 */
@EnableEurekaClient              // 尤里卡客户端
@EnableConfigServer              // 启动分布式配置中心服务端
@SpringBootApplication
public class ConfigApp {
}
