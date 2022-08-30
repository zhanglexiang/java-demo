package com.zx.validation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zx.validation.constraints.Sex;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    @NotNull(groups = Update.class)
    @Null(groups = Save.class)
    private Long userId;
    @NotBlank
    private String name;
    @Range(max = 100, min = 18)
    private int age;
    @Sex
    private String sex;
    @Email
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Past
    private Date birthday;
    @Valid
    @NotNull
    private Address address;
    @Valid
    @NotNull
    private List<Address> addressList;

    @Data
    public static class Address{
        @Length(min = 6, max = 20)
        @NotBlank
        private String detailed;
    }

    // 保存的时候校验分组
    public interface Save { }
    //更新的时候校验分组
    public interface Update {}

}
