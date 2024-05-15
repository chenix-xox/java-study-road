package com.chenix.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Chenix
 * @create_date 2024/5/6 14:29
 */
@Getter
public enum SexEnum {
    MALE(0,"男"),

    FEMALE(1,"女");

    @EnumValue
    private final Integer sex;

    private final String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
