package cn.king.domain.vo;

import cn.king.domain.entity.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/4 21:45
 * @version: 1.0.0
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonVO extends Person {

    private static final long serialVersionUID = 2265065846820904241L;

    /**
     * 开始生日
     */
    // 响应到前台的格式
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 前台需要传递的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginBirthday;

    /**
     * 结束生日
     */
    // 响应到前台的格式
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 前台需要传递的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endBirthday;

}
