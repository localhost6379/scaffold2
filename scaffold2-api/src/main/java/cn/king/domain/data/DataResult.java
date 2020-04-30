package cn.king.domain.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/17 16:36
 * @version: 1.0.0
 * @description:
 */
@Data
@ApiModel("统一返回值，有泛型")
public class DataResult<T> {

    /**
     * 响应码
     */
    @ApiModelProperty("响应码")
    private Integer code = HttpStatus.OK.value();

    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    public DataResult() {
    }

    public DataResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DataResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataResult(String message) {
        this.message = message;
    }

    public DataResult(T data) {
        this.data = data;
    }

    public static DataResult error(Integer code, String message) {
        return new DataResult(code, message);
    }

    public static DataResult error(String message) {
        return new DataResult(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static DataResult error() {
        return new DataResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
    }

    //public static DataResult ok(T data) {
    //    DataResult<T> dataResult = new DataResult<>();
    //    dataResult.setData(data);
    //    return dataResult;
    //}

    public static DataResult ok(String message) {
        DataResult dataResult = new DataResult();
        dataResult.setMessage(message);
        return dataResult;
    }

    public static DataResult ok() {
        return new DataResult();
    }

}