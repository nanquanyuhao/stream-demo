package net.nanquanyuhao.advice;

import net.nanquanyuhao.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 处理器切面，表明当前切面的连接点为处理器方法
 */
@ControllerAdvice
public class ParamValidatorAdvice {

    // 定义异常处理器
    @ExceptionHandler
    public ResponseEntity<String> validateHandler(StudentException ex) {
        String msg = ex.getErrorField() + "【" + ex.getMessage() + ":" + ex.getErrorValue() + "】";
        return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
    }

    // 定义异常处理器
    @ExceptionHandler
    public ResponseEntity<String> validateHandler(WebExchangeBindException ex) {
        return new ResponseEntity<String>(exToStr(ex), HttpStatus.BAD_REQUEST);
    }

    /**
     * 将异常对象 WebExchangeBindException 转换为异常信息 String
     *
     * @param ex
     * @return
     */
    private String exToStr(WebExchangeBindException ex) {
        return ex.getFieldErrors()    // 集合，元素是出现异常的属性异常信息
                .stream()   // stream，元素为出现异常的属性异常信息
                .map(e -> e.getField() + ":" + e.getDefaultMessage())  // 将 Stream 中的异常类型元素映射为了 String 类型元素
                .reduce("", (s1, s2) -> s1 + "\n" + s2);  // 将 Stream 中的 String 元素拼接为一个 String
    }
}
