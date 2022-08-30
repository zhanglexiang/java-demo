package com.zx.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @date
 * @revisionHistory
 **/
@Data
public class HttpResult<T> {

    @ApiModelProperty(value="返回值 ")
    protected T data;

    @ApiModelProperty(value="状态码:-1需要登入 -2需要弹框提醒")
    protected int code;

    @ApiModelProperty(value="错误信息")
    protected String errMsg;

    @ApiModelProperty(value="是否成功")
    protected boolean success;

    @ApiModelProperty(value="从第几页开始")
    protected Long pageNum;

    @ApiModelProperty(value="总页数")
    protected Integer totalPage;

    @ApiModelProperty(value="总数据量 ")
    protected Long totalRecords;

    public HttpResult(){}

    public HttpResult(boolean success){
        this.success = success;
    }

    public HttpResult(T data){
        this.data = data;
        this.code = 200;
        this.success = true;
    }

    public HttpResult(String errMsg,int code){
        this.errMsg = errMsg;
        this.code = code;
        this.success = false;
    }

    public HttpResult(T data,String errMsg,int code){
        this.data = data;
        this.errMsg = errMsg;
        this.code = code;
        this.success = false;
    }



    public HttpResult(T data, Integer totalPage, Long totalRecords) {
        super();
        this.data = data;
        this.totalPage = totalPage;
        this.totalRecords = totalRecords;
        this.code = 200;
        this.success = true;
    }
    
    public long getTotalPage(Integer size){
        if (size != null && size != 0) {
            Long total = getTotalRecords();
            if (total != null && total.longValue() > 0L) {
                return total/size+(total%size>0?1:0);
            }
        }
        return 0L;
    }


    public static <T> HttpResult<T> success(T responseData,Integer pageSize, Long total) {
        HttpResult<T> entity = new HttpResult<>();
        entity.setTotalRecords(total);
        entity.setTotalPage((int) entity.getTotalPage(pageSize));
        entity.setData(responseData);
        entity.setSuccess(true);
        return entity;
    }

    public static <T> HttpResult<List<T>> success(List<T> listData, Integer pageSize, Long total) {
        HttpResult<List<T>> entity = new HttpResult<>();
        entity.setTotalRecords(total);
        entity.setTotalPage((int) entity.getTotalPage(pageSize));
        entity.setData(listData);
        entity.setSuccess(true);
        return entity;
    }


    public static <T> HttpResult<List<T>> success(List<T> listData, Long pageNum,Long total) {
        HttpResult<List<T>> entity = new HttpResult<>();
        entity.setPageNum(pageNum);
        entity.setTotalRecords(total);
        entity.setData(listData);
        entity.setSuccess(true);
        return entity;
    }

    public static <T> HttpResult<T> success(T t) {
        HttpResult<T> entity = new HttpResult<>();
        entity.setSuccess(true);
        entity.setData(t);
        return entity;
    }


    public static HttpResult<?> success() {
        HttpResult<?> entity = new HttpResult<>();
        entity.setSuccess(true);
        return entity;
    }


    public static <T> HttpResult<T> fail(String errMsg) {
        HttpResult<T> entity = new HttpResult<>();
        entity.setSuccess(false);
        entity.setErrMsg(errMsg);
        return entity;
    }


    public static <T> HttpResult<T> fail(int code, String errMsg, T t) {
        HttpResult<T> entity = new HttpResult<>();
        entity.setSuccess(false);
        entity.setCode(code);
        entity.setErrMsg(errMsg);
        entity.setData(t);
        return entity;
    }


    public static <T> HttpResult<T> fail(String errMsg, Class<T> t) {
        HttpResult<T> entity = new HttpResult<>();
        entity.setSuccess(false);
        entity.setErrMsg(errMsg);
        return entity;
    }


    public static <T> HttpResult<T> success(int code, String errMsg, T t) {
        HttpResult<T> entity = new HttpResult<>();
        entity.setSuccess(true);
        entity.setCode(code);
        entity.setErrMsg(errMsg);
        entity.setData(t);
        return entity;
    }

}
