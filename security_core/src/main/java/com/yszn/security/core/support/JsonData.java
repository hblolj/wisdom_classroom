package com.yszn.security.core.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hblolj
 * @Date: 2018/5/2 14:43
 * @Description:
 * @Version: 1.0
 **/
@ApiModel
public class JsonData<T> implements Serializable {

    @ApiModelProperty(value = "状态码", name = "状态码")
    private Integer ret;

    @ApiModelProperty(value = "状态码描述", name = "状态码描述")
    private String message;

    @ApiModelProperty(value = "数据对象", name = "数据对象")
    private T data;

    public JsonData(Integer ret) {
        this.ret = ret;
    }

    public JsonData() {
    }

    public JsonData(Integer ret, String message, T data) {
        this.ret = ret;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonData<T> success(T data, String msg) {
        JsonData jsonData = new JsonData(0);
        jsonData.data = data;
        jsonData.message = msg;
        return jsonData;
    }

    public static <T> JsonData<T> success(T object) {
        JsonData jsonData = new JsonData(0);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(0);
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(1);
        jsonData.message = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>(3);
        result.put("ret", ret);
        result.put("msg", message);
        result.put("data", data);
        return result;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
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
}
