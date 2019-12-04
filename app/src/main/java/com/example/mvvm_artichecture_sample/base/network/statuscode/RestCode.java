package com.example.mvvm_artichecture_sample.base.network.statuscode;

public class RestCode {
    public static final int OK_STATUS_CODE = 200;
    public static final int CREATED_STATUS_CODE = 201;
    public static final int NO_CONTENT_STATUS_CODE = 204;
    public static final int BAD_REQUEST_STATUS_CODE = 400;
    public static final int UNAUTHORIZED_STATUS_CODE = 401;
    public static final int FORBITTEN_STATUS_CODE = 403;
    public static final int NOT_FOUND_STATUS_CODE = 404;
    public static final int PRECONDITION_STATUS_CODE = 412;
    public static final int FAILD_DEPEDENCY_STATUS_CODE = 424;
    public static final int NOT_ACCEPTABLE_STATUS_CODE = 406;
    public static final int SERVER_ERROR_STATUS_CODE = 500;
    public static final int NETWORK_CONNECT_TIMEOUT_CODE = 599;

    public static boolean isInternalServerError(int statusCode) {
        return RestCode.SERVER_ERROR_STATUS_CODE <= statusCode && statusCode <= NETWORK_CONNECT_TIMEOUT_CODE;
    }
}
