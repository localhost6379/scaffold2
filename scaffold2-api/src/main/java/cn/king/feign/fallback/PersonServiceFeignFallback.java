package cn.king.feign.fallback;

import cn.king.domain.data.DataResult;
import cn.king.domain.data.PageBean;
import cn.king.domain.entity.Person;
import cn.king.feign.service.PersonServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/5 18:14
 * @version: 1.0.0
 * @description: 熔断回调
 */
@Slf4j
@Component
public class PersonServiceFeignFallback implements PersonServiceFeign {

    @Override
    public DataResult save(Person entity) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.save 熔断了!");
        return this.fallback();
    }

    @Override
    public DataResult deleteById(Long id) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.deleteById 熔断了!");
        return this.fallback();
    }

    @Override
    public DataResult delete(Person entity) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.delete");
        return this.fallback();
    }

    @Override
    public DataResult update(Person entity) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.update 熔断了!");
        return this.fallback();
    }

    @Override
    public DataResult<Person> findById(Long id) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.findById 熔断了!");
        return new DataResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "熔断了!");
    }

    @Override
    public DataResult<List<Person>> findAllByNameLike(String name) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.findAllByNameLike 熔断了!");
        return new DataResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "熔断了!");
    }

    @Override
    public DataResult<List<Person>> findAll() {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.findAll 熔断了!");
        return new DataResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "熔断了!");
    }

    @Override
    public DataResult<PageBean<Person>> findByCondition(Integer pageNumber, Integer pageSize, Person queryCondition) {
        log.error("cn.king.feign.fallback.PersonServiceFeignFallback.findByCondition 熔断了!");
        return new DataResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "熔断了!");
    }

    private DataResult fallback() {
        return DataResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "熔断了!");
    }

}
