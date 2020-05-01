package cn.king.domain.entity;

import cn.king.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/4 21:36
 * @version: 1.0.0
 * @description:
 */
@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_person")
public class Person extends BaseEntity {

    private static final long serialVersionUID = -4273818904889782914L;

    @Column(name = "gender")
    private Byte gender;

    // 响应到前台的格式
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 前台需要传递的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "salary")
    private Double salary;

    /**
     * 是否结婚
     */
    @Column(name = "is_married")
    private Boolean isMarried;

}
