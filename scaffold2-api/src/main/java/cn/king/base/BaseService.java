package cn.king.base;

import cn.king.domain.data.PageBean;
import cn.king.domain.vo.PageVO;
import cn.king.util.DateTimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 基础Service，包含一些通用的方法
 */
public interface BaseService<E extends BaseEntity, ID extends Serializable> {

    BaseDao<E, ID> getDao();

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:15
     * @param: entity
     * @return: E
     * @description: 添加
     */
    default E save(E entity) {
        return this.getDao().save(entity);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:15
     * @param: id
     * @return: void
     * @description: 根据id删除
     */
    default void deleteById(ID id) {
        if (getDao().existsById(id)) {
            getDao().deleteById(id);
        }
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:16
     * @param: entity
     * @return: void
     * @description: 条件删除
     */
    default void delete(E entity) {
        this.getDao().delete(entity);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:16
     * @param: entity
     * @return: E
     * @description: 修改. 注意是替换.
     * https://blog.csdn.net/NathanniuBee/article/details/90482101
     */
    default E update(E entity) {
        return getDao().saveAndFlush(entity);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:17
     * @param:
     * @return: java.util.List<E>
     * @description: 查询全部
     */
    default List<E> findAll() {
        return getDao().findAll();
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:17
     * @param: pageVO
     * @param: queryCondition
     * @return: org.springframework.data.domain.Page<E>
     * @description: 条件查询+分页
     */
    //default Page<E> findByCondition(PageVO pageVO, E queryCondition) {
    default PageBean<E> findByCondition(PageVO pageVO, E queryCondition) {

        if (pageVO == null) pageVO = new PageVO();

        // 按照排序值降序，排序值为int类型，值越大数据越靠前。排序值相同按照最后更新时间降序。
        Sort sort = Sort.by(Sort.Order.desc("sortOrder"), Sort.Order.asc("updatedTime"));

        Pageable pageable = PageRequest.of(pageVO.getPageNumber() - 1, pageVO.getPageSize(), sort);

        if (queryCondition == null) return this.pageToPageBean(getDao().findAll(pageable));

        Specification<E> specification = new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // 所有的断言
                List<Predicate> predicateList = new ArrayList<>();

                // 默认搜索条件
                Path<String> nameField = root.get("name");
                Path<Integer> statusField = root.get("status");

                if (!StringUtils.isEmpty(queryCondition.getName())) {
                    predicateList.add(cb.like(nameField, "%" + queryCondition.getName() + "%"));
                }
                if (queryCondition.getStatus() != null) {
                    predicateList.add(cb.equal(statusField, queryCondition.getStatus()));
                }

                Optional<Date> createdTimeBeginOptional = Optional.ofNullable(queryCondition.getCreatedTimeBegin());
                Optional<Date> createdTimeEndOptional = Optional.ofNullable(queryCondition.getCreatedTimeEnd());
                Optional<Date> updatedTimeBeginOptional = Optional.ofNullable(queryCondition.getUpdatedTimeBegin());
                Optional<Date> updatedTimeEndOptional = Optional.ofNullable(queryCondition.getUpdatedTimeEnd());

                createdTimeBeginOptional.ifPresent(createdTimeBegin -> predicateList.add(cb.greaterThanOrEqualTo(root.get("createdTime").as(String.class), DateTimeUtils.getDateTime(createdTimeBegin))));
                createdTimeEndOptional.ifPresent(createdTimeEnd -> predicateList.add(cb.lessThanOrEqualTo(root.get("createdTime").as(String.class), DateTimeUtils.getDateTime(createdTimeEnd))));
                updatedTimeBeginOptional.ifPresent(updatedTimeBegin -> predicateList.add(cb.greaterThanOrEqualTo(root.get("updatedTime").as(String.class), DateTimeUtils.getDateTime(updatedTimeBegin))));
                updatedTimeEndOptional.ifPresent(updatedTimeEnd -> predicateList.add(cb.lessThanOrEqualTo(root.get("updatedTime").as(String.class), DateTimeUtils.getDateTime(updatedTimeEnd))));

                Predicate[] arr = new Predicate[predicateList.size()];
                // 构造条件And
                return cb.and(predicateList.toArray(arr));
            }
        };

        //return getDao().findAll(specification, pageable);
        Page<E> page = getDao().findAll(specification, pageable);

        /*
         * 该查询返回一个Page实例, 具体的实现是org.springframework.data.domain.PageImpl.
         * 问题就出在这个PageImpl对象, 正常情况下没有任何问题, 但是如果这个对象通过Feign中转时, 就会出现无法反序列化的错误.
         * 究其原因, 是PageImpl没有无参构造, 其超类Chunk也没有无参构造; 导致反序列化失败.
         * 解决的方法有两种, 一是自定义反序列化, 比较麻烦.
         * 另一种办法就是自定义Page, 放弃Spring自带的PageImpl, 这就解决了反序列化的问题. 此处使用的是后一种方法.
         */
        return this.pageToPageBean(page);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:25
     * @param: name
     * @return: java.util.List<E>
     * @description: 根据名字模糊查询
     */
    default List<E> findAllByNameLike(String name) {
        return getDao().findAllByNameLike("%" + name + "%");
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:16
     * @param: id
     * @return: E
     * @description: 根据id查询. 不存在返回null;
     */
    default E findById(ID id) {
        return getDao().findById(id).orElse(null);
    }

    // ---------- 下面的方法未在BaseController中调用 --------------

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/5 16:27
     * @param: entity
     * @return: E
     * @description: 添加并刷新
     */
    default E saveAndFlush(E entity) {
        return getDao().saveAndFlush(entity);
    }

    default Page<E> findAll(Pageable pageable) {
        return getDao().findAll(pageable);
    }

    default Page<E> findAll(Specification<E> specification, Pageable pageable) {
        return getDao().findAll(specification, pageable);
    }

    default void flush() {
        this.getDao().flush();
    }

    default long count() {
        return getDao().count();
    }

    default long count(Specification<E> specification) {
        return getDao().count(specification);
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/4/30 20:15
     * @param: page
     * @return: cn.king.domain.data.PageBean<E>
     * @description: SpringDataJPA自带的Page转自定义的PageBean
     */
    default PageBean<E> pageToPageBean(Page<E> page) {
        PageBean<E> pageBean = new PageBean<>();
        pageBean.setPageNumber(page.getNumber() + 1);
        pageBean.setPageSize(page.getSize());
        pageBean.setTotalRecords(page.getTotalElements());
        pageBean.setBeanList(page.getContent());
        return pageBean;
    }

}
