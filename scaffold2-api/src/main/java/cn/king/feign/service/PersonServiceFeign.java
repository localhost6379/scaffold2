package cn.king.feign.service;

import cn.king.domain.data.DataResult;
import cn.king.domain.data.PageBean;
import cn.king.domain.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(value = "SCAFFOLD2-PRODUCER", fallback = PersonServiceFeignFallback.class)
@FeignClient(value = "SCAFFOLD2-PRODUCER")
public interface PersonServiceFeign {

    String PREFIX = "/person";

    @PostMapping(PREFIX)
    DataResult<Person> save(@RequestBody Person entity);

    @DeleteMapping(PREFIX + "/{id}")
    DataResult deleteById(@PathVariable Long id);

    @DeleteMapping(PREFIX)
    DataResult delete(@RequestBody Person entity);

    @PutMapping(PREFIX)
    DataResult update(@RequestBody Person entity);

    @GetMapping(PREFIX + "/{id}")
    DataResult<Person> findById(@PathVariable Long id);

    @GetMapping(PREFIX + "/name/{name}")
    DataResult<List<Person>> findAllByNameLike(@PathVariable String name);

    @GetMapping(PREFIX)
    DataResult<List<Person>> findAll();

    @PostMapping(PREFIX + "/list/{pageNumber}/{pageSize}")
    DataResult<PageBean<Person>> findByCondition(@PathVariable Integer pageNumber,
                                                 @PathVariable Integer pageSize,
                                                 @RequestBody(required = false) Person queryCondition);

}
