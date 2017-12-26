package com.system.aym.common.bean;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

    public static final String ERRORS_KEY = "errors";

    private final String message;

    private final int code;

    private final Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    /**
     * 未找到
     * Not Found
     * 404
     *
     * @return
     */
    public static ResponseData notFound() {
        return new ResponseData(404, "未找到");
    }

    /**
     * 错误的请求
     * Bad Request
     * 400
     *
     * @return
     */
    public static ResponseData badRequest() {
        return new ResponseData(400, "错误的请求");
    }

    /**
     * 禁用的
     * Forbidden
     * 403
     *
     * @return
     */
    public static ResponseData forbidden() {
        return new ResponseData(403, "禁用的");
    }

    /**
     * 未被授权的
     * unauthorized
     * 401
     *
     * @return
     */
    public static ResponseData unauthorized() {
        return new ResponseData(401, "未被授权的");
    }

    /**
     * 服务器内部错误
     * Server Internal Error
     * 500
     *
     * @return
     */
    public static ResponseData serverInternalError() {
        return new ResponseData(500, "服务器内部错误");
    }

    /**
     * 客户错误
     * Customer Error
     * 1001
     *
     * @return
     */
    public static ResponseData customerError() {
        return new ResponseData(1001, "客户错误");
    }

    /**
     * 非法的请求参数
     * Invalid request
     * 10000
     *
     * @return
     */
    public static ResponseData paramError() {
        return new ResponseData(10000, "非法的请求参数");
    }

    /**
     * 指定的对象已存在
     * Specified object already exists
     * 804
     *
     * @return
     */
    public static ResponseData objectExists() {
        return new ResponseData(804, "对象已存在");
    }
}
