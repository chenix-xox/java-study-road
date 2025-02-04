package com.chenix.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Chenix
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;
    /**
     * 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java
     */
    private String message;
    private T data;
    private long timestamp;


    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> success(T data, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> fail(ReturnCodeEnum returnCodeEnum) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(returnCodeEnum.getCode());
        resultData.setMessage(returnCodeEnum.getMessage());
        resultData.setCode(resultData.getCode());
        resultData.setMessage(resultData.getMessage());
        return resultData;
    }
}