package cn.king.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/2 17:05
 * @version: 1.0.0
 * @description: http://localhost:8082/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 要扫描的生成接口文档的包
                .apis(RequestHandlerSelectors.basePackage("cn.king.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 大标题
                .title("XXX微服务接口文档")
                // 描述
                .description("XXX接口,这个接口有一些xxxx")
                // 网络服务地址
                .termsOfServiceUrl("http://www.ddit.com")
                // 版本号 自定义
                .version("1.0.0")
                .build();
    }

}
