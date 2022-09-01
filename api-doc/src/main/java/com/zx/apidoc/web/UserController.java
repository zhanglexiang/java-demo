package com.zx.apidoc.web;

import com.zx.common.result.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value="用户id", paramType="path", required=true),
    })
    @GetMapping("{userId}")
    public HttpResult detail(@PathVariable("userId") @Min(100L) Long userId) {
        return HttpResult.success();
    }

    /**
     * @api {GET} /user/queryUserByName 根据姓名查询用户
     * @apiName 根据姓名查询用户
     * @apiVersion 1.0.0
     * @apiDescription 描述
     * @apiGroup 用户接口
     * @apiParam {String} name 用户姓名
     * @apiExample {curl} Example usage:
     *     curl -X POST http://127.0.0.1:21001/queryUserByName?name=张三 \
     *     -H "Content-Type:application/json" \
     *     -H "Authorization: Bearer <Access-Token>"
     * @apiSuccess HttpResult
     */
    @GetMapping("queryUserByName")
    public HttpResult queryUserByName(@RequestParam("name") String name) {
        return HttpResult.success();
    }


}
