package cn.king.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author: wjl@king.cn
 * @time: 2020/5/1 15:07
 * @version: 1.0.0
 * @description:
 */
@Configuration
public class AuditorConfig implements AuditorAware<String> {

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/5/1 15:11
     * @param:
     * @return: java.util.Optional<java.lang.String>
     * @description: 自动将创建人、最后更新人设置为admin。
     * 配合@EntityListeners(AuditingEntityListener.class)、@EnableJpaAuditing等注解使用
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin");
    }

}
