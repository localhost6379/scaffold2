package cn.king.exception;


import cn.king.domain.data.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wjl@king.cn
 * @time: 2020/2/22 14:21
 * @version: 1.0.0
 * @description:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionResolver {

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/2/22 15:04
     * @param: e
     * @return: cn.king.common.AjaxResult
     * @description: 处理所有Exception异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public DataResult handleException(Exception e) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        return DataResult.error();
    }

}
