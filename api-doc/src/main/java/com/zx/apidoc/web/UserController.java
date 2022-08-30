package com.zx.apidoc.web;

import com.zx.common.result.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

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

    @ApiOperation(value = "查询用户")
    @GetMapping("{userId}")
    public HttpResult detail(@PathVariable("userId") @Min(100L) Long userId) {
        return HttpResult.success();
    }


}
