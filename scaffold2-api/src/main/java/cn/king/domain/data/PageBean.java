package cn.king.domain.data;

import lombok.*;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: wjl@king.cn
 * @createTime: 2019/12/8 20:20
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
    /**
     * 当前页码
     */
    private Integer pageNumber;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long totalRecords;
    /**
     * 每页显示的数据
     */
    private List<T> beanList;

    /**
     * 获取起始索引
     *
     * @return
     */
    public Integer getBeginIndex() {
        return (pageNumber - 1) * pageSize;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public Long getPageNumberAll() {
        return totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
    }

}
