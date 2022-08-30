package com.zx.validation.util;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationList<E> implements List<E> {

    @Delegate // @Delegate是lombok注解（1.18.6版本以上）,将对ValidationList的调用委托到list中
    @Valid // 一定要加@Valid注解
    public List<E> list = new ArrayList<>();
    
    /**
     * 必须要重写toString方法
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
