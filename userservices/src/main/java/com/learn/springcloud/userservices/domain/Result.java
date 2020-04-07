package com.learn.springcloud.userservices.domain;

import java.io.Serializable;

/**
 * @author: liuhuan
 * @Desc: 返回对象
 * @time: 2020/4/4 11:41
 */
public class Result<T> implements Serializable {

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer FAIL_CODE = 500;

    private Integer retCode;

    private String message;

    private T data;

    public Result() {
    }

    public Result(Integer retCode, String message, T data) {
        this.retCode = retCode;
        this.message = message;
        this.data = data;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param message 信息
     * @param data    对象
     * @return 结果对象
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(SUCCESS_CODE, message, data);
    }

    /**
     * 失败返回结果
     *
     * @param message 信息
     * @param data    返回对象
     * @return 结果对象
     */
    public static <T> Result<T> fail(String message, T data) {
        return new Result<>(FAIL_CODE, message, data);
    }
}
