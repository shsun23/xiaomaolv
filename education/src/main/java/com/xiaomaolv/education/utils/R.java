package com.xiaomaolv.education.utils;


import java.util.HashMap;
import java.util.Map;

// 统一返回结果的数据结构
public class R {

    // 是否成功
    private Boolean success;
    // 返回码
    private Integer code;

    // 返回信息
    private String message;
    // 返回的具体数据
    private Map<String, Object> data = new HashMap<String, Object>();


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    // 类的构造方法改成私有
    private R() { }

    // 成功静态方法
    public static R ok(){
        R r =  new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("success!");
        return r;
    }

    // 失败静态方法
    public static R error(){
        R r =  new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("failed!");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
