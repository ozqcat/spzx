package com.ozq.spzx.model.enums;

import lombok.Getter;

/**
 * @Author ozq
 * @Date 2023/11/6 22:49
 * @Description
 */
@Getter
public enum LoginEnum {

    USER_LOGIN("user:login:"),
    USER_VALIDATE("user:validate:"),
    ;
    private String value;
    private LoginEnum(String value){
        this.value = value;
    }
}
