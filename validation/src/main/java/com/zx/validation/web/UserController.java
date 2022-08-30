package com.zx.validation.web;

import com.zx.common.result.HttpResult;
import com.zx.validation.dto.UserDTO;
import com.zx.validation.util.ValidationList;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @author
 * @date
 * @revisionHistory
 **/
@Slf4j
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private javax.validation.Validator validator;

    // 编程式校验
    @PostMapping("/saveUser2")
    public HttpResult saveUser2(@RequestBody UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO, UserDTO.Save.class);
        // 如果校验通过，validate为空；否则，validate包含未校验通过项
        if (validate.isEmpty()) {
            // 校验通过，才会执行业务逻辑处理

        } else {
            for (ConstraintViolation<UserDTO> userDTOConstraintViolation : validate) {
                // 校验失败，做其它逻辑
                log.info("userDTOConstraintViolation : {}", userDTOConstraintViolation);
            }
        }
        return HttpResult.success();
    }

    @PostMapping("addUser")
    public HttpResult addUser(@RequestBody @Validated({UserDTO.Save.class, Default.class}) UserDTO saveUserDTO){
        log.info("saveUserDTO : {}", saveUserDTO);
        return HttpResult.success("添加用户成功!");
    }

    @PostMapping("updateUser")
    public HttpResult updateUser(@RequestBody @Validated({UserDTO.Update.class, Default.class}) UserDTO updateUserDTO){
        log.info("updateUserDTO : {}", updateUserDTO);
        return HttpResult.success("修改用户成功!");
    }

    @PostMapping("batchAddUser")
    public HttpResult batchAddUser(@RequestBody @Validated({UserDTO.Save.class, Default.class}) ValidationList<UserDTO> userDTOList){
        log.info("userDTOList : {}", userDTOList);
        return HttpResult.success("批量添加用户成功!");
    }

    // PathVariable 传参
    @GetMapping("{userId}")
    public HttpResult detail(@PathVariable("userId") @Min(100L) Long userId) {
        return HttpResult.success();
    }

    // @RequestParam 传参
    @GetMapping("getByName")
    public HttpResult getByName(@Length(min = 8, max = 20) @NotNull String  name) {
        return HttpResult.success();
    }

}
