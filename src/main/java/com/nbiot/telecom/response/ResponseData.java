package com.nbiot.telecom.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户ajax提交之后的相应结果
 * <p>
 * Created by wangkun23 on 2018/12/19.
 */
@ToString
public class ResponseData implements Serializable {

    /**
     * 消息code
     */
    @Setter
    @Getter
    private String code;

    /**
     * 错误提示
     */
    @Setter
    @Getter
    private String message;

    /**
     * 结果数据
     * 如果返回对象或数组直接用setData
     * 如果返回简单的键值对用addData
     */
    @Setter
    @Getter
    private Object data;

    private Map<String, Object> dataShadow;

    public ResponseData() {

    }

    public ResponseData(ResponseCode code) {
        this.code = code.getKey();
        this.message = code.getRemark();
    }

    public ResponseData(ResponseCode code, String message) {
        this.code = code.getKey();
        this.message = message;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static ResponseData success() {
        return new ResponseData(ResponseCode.SUCCESS);
    }

    /**
     * 返回成功消息
     *
     * @param code 消息code
     * @return 成功消息
     */
    public static ResponseData success(ResponseCode code) {
        return new ResponseData(code);
    }

    /**
     * 返回错误
     *
     * @return
     */
    public static ResponseData error() {
        return new ResponseData(ResponseCode.ERROR);
    }

    /**
     * 返回错误消息
     *
     * @param code 消息code
     * @return 错误消息
     */
    public static ResponseData error(ResponseCode code) {
        return new ResponseData(code);
    }

    /**
     * 返回错误消息
     *
     * @param code 消息code
     * @return 错误消息
     */
    public static ResponseData error(ResponseCode code, String error) {
        return new ResponseData(code, error);
    }

    public static ResponseData nbiotError(String code) {
        if (code.equals(ResponseCode.ERROR_NBIOT_INTERNAL_SERVER_ERROR.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_INTERNAL_SERVER_ERROR);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_INVALID_TOKEN.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_INVALID_TOKEN);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_INVALID_VERIFY_CODE.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_INVALID_VERIFY_CODE);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_BAD_REQUEST_MESSAGE.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_BAD_REQUEST_MESSAGE);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_INPUT_INVALID.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_INPUT_INVALID);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_APP_ID_NOT_EXISTED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_APP_ID_NOT_EXISTED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_GET_APP_KEY_FAIL.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_GET_APP_KEY_FAIL);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_APPLICATION_NOT_EXISTED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_APPLICATION_NOT_EXISTED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_APPLICATION_NOT_AUTHORIZED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_APPLICATION_NOT_AUTHORIZED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_DEVICE_COUNT_REACHED_LIMIT.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_DEVICE_COUNT_REACHED_LIMIT);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_DEVICE_HAS_BEAN_BINDED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_DEVICE_HAS_BEAN_BINDED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_DEVICE_DATA_NOT_EXISTED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_DEVICE_DATA_NOT_EXISTED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_SERVICE_TYPE_NOT_EXISTED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_SERVICE_TYPE_NOT_EXISTED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_LICENSE_NOT_EXISTED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_LICENSE_NOT_EXISTED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_LICENSE_SALES_NOT_EXISTED.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_LICENSE_SALES_NOT_EXISTED);
        } else if (code.equals(ResponseCode.ERROR_NBIOT_LICENSE_POOL_RESOURCES.getKey())) {
            return error(ResponseCode.ERROR_NBIOT_LICENSE_POOL_RESOURCES);
        } else {
            return error(ResponseCode.ERROR);
        }
    }

    /**
     * 增加数据绑定
     *
     * @param value
     */
    public ResponseData with(Object value) {
        this.setData(value);
        return this;
    }

    /**
     * 添加返回的数据
     *
     * @param key
     * @param value
     */
    public void addData(String key, Object value) {
        if (this.dataShadow == null) {
            this.dataShadow = new LinkedHashMap<String, Object>();

            this.data = this.dataShadow;
        }
        this.dataShadow.put(key, value);
    }

    /**
     * @param key
     * @return
     */
    public Object removeData(String key) {
        return this.dataShadow != null ? this.dataShadow.remove(key) : null;
    }
}
