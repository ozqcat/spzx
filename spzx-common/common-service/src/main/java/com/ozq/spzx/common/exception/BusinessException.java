package com.ozq.spzx.common.exception;

import com.ozq.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @Author ozq
 * @Date 2023/11/13 20:52
 * @Description
 */
@Data
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;

    private ResultCodeEnum resultCodeEnum;

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public BusinessException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
