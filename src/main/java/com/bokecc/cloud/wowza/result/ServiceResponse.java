package com.bokecc.cloud.wowza.result;

import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.enums.ResponseEnum;
import com.bokecc.cloud.wowza.enums.WowzaRespEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private String version;
    private T payLoad;

    public ServiceResponse() {
        this.setCode(ResponseEnum.SUCCESS.getCode());
        this.setMessage(ResponseEnum.SUCCESS.getMsg());
    }

    public ServiceResponse(T data) {
        this.setCode(ResponseEnum.SUCCESS.getCode());
        this.setMessage(ResponseEnum.SUCCESS.getMsg());
        this.data = data;
    }

    public ServiceResponse(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ServiceResponse(ResponseEnum responseEnum) {
        this.setCode(responseEnum.getCode());
        this.setMessage(responseEnum.getMsg());
    }

    public ServiceResponse(ResponseEnum responseEnum, T data) {
        this.setCode(responseEnum.getCode());
        this.setMessage(responseEnum.getMsg());
        this.data = data;
    }


    public void setResponse(ResponseEnum responseEnum) {
        this.setCode(responseEnum.getCode());
        this.setMessage(responseEnum.getMsg());
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public T getData() {
        return data;
    }

    public ServiceResponse<T> setData(T data) {
        if (data != null && data instanceof JSONObject) {
            String codeStr = ((JSONObject)data).getString(WowzaRespEnum.RESP_API_CODE_KEY.getValue());
            if (codeStr != null && StringUtils.isNotEmpty(codeStr.trim())) {
                Integer codeTemp = Integer.valueOf(codeStr);
                String messageTemp = ((JSONObject)data).getString("message");
                if (codeTemp != null) {
                    code = codeTemp;
                    message = messageTemp;
                }
            }
        }
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public T getPayLoad() {
        return this.payLoad;
    }

    public ServiceResponse<T> setPayLoad(T payLoad) {
        this.payLoad = payLoad;
        return this;
    }

    public void getResponse(ResponseEnum responseEnum){
        this.message = responseEnum.getMsg();
        this.code = responseEnum.getCode();
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }
}
