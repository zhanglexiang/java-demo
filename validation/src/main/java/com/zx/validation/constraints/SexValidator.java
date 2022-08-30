package com.zx.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 实现ConstraintValidator接口编写约束校验器
 * @author zl
 * @date 8/26/22 3:33 PM
 * @return {@link null}
 */
public class SexValidator implements ConstraintValidator<Sex, String> {

    private static final String MAN = "男";

    private static final String WOMAN = "女";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 不为null才进行校验
        if (value != null) {
            if(!Objects.equals(value,MAN) && !Objects.equals(value,WOMAN)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
