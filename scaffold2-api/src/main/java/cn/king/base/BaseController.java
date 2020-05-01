package cn.king.base;

import cn.king.annotation.Log;
import cn.king.domain.data.DataResult;
import cn.king.domain.data.PageBean;
import cn.king.domain.vo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/5 14:35
 * @version: 1.0.0
 * @description:
 */
@Slf4j
@RestController
@Transactional(rollbackFor = RuntimeException.class)
@ApiModel("基础接口, 每个Controller中都有这些接口")
public abstract class BaseController<E extends BaseEntity, ID extends Serializable> {

    @Autowired
    public abstract BaseService<E, ID> getService();

    @Log
    @PostMapping
    @ApiOperation("添加")
    public DataResult save(@RequestBody E entity) {
        E e = this.getService().save(entity);
        return e == null ? DataResult.error() : DataResult.ok();
    }

    @Log
    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public DataResult deleteById(@PathVariable ID id) {
        this.getService().deleteById(id);
        return DataResult.ok();
    }

    @Log
    @ApiOperation("删除")
    @DeleteMapping
    public DataResult delete(@RequestBody E entity) {
        this.getService().delete(entity);
        return DataResult.ok();
    }

    @Log
    @ApiOperation("修改。有id就修改，没有id就添加。")
    @PutMapping
    public DataResult update(@RequestBody E entity) {
        E e = this.getService().update(entity);
        return e == null ? DataResult.error() : DataResult.ok();
    }

    @Log
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public DataResult<E> findById(@PathVariable ID id) {
        E e = this.getService().findById(id);
        return e == null ? new DataResult<E>(500, "系统错误") : new DataResult<E>(e);
    }

    @Log
    @ApiOperation("根据名字模糊查询")
    @GetMapping("/name/{name}")
    public DataResult<List<E>> findAllByNameLike(@PathVariable String name) {
        List<E> list = getService().findAllByNameLike(name);
        return list == null || list.isEmpty() ? new DataResult<List<E>>(500, "系统错误") : new DataResult<List<E>>(list);
    }

    @Log
    @ApiOperation("查询全部")
    @GetMapping
    public DataResult<List<E>> findAll() {
        List<E> list = this.getService().findAll();
        return list == null || list.isEmpty() ? new DataResult<List<E>>(500, "系统错误") : new DataResult<List<E>>(list);
    }

    @Log
    @ApiOperation("条件查询并分页。支持：name模糊查询、status精确查询、创建时间范围查询、更新时间范围查询。默认排序：按照排序值降序，排序值相同按照更新时间升序。")
    @PostMapping("/list/{pageNumber}/{pageSize}")
    public DataResult<PageBean<E>> findByCondition(@PathVariable Integer pageNumber,
                                               @PathVariable Integer pageSize,
                                               @RequestBody(required = false) E queryCondition) {
        PageBean<E> pageBean = getService().findByCondition(new PageVO(pageNumber, pageSize), queryCondition);
        return pageBean == null ? new DataResult<PageBean<E>>(500, "系统错误") : new DataResult<PageBean<E>>(pageBean);
    }

}
