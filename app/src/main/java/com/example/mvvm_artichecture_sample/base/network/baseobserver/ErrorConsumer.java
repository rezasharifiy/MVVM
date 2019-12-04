package com.example.mvvm_artichecture_sample.base.network.baseobserver;

import com.example.mvvm_artichecture_sample.base.network.model.APIError;
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class ErrorConsumer implements Consumer<Throwable> {


    private WebServiceListener listener;
    private String requestType;

    public ErrorConsumer(WebServiceListener listener, String requestType) {
        this.listener = listener;
        this.requestType = requestType;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        List<APIError> list = new ArrayList<>();
        APIError apiError = new APIError();
        int code;
        if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
            apiError.setCode(ClientCode.NO_INTERNET_TIMEOUT_CODE);
            code = ClientCode.NO_INTERNET_TIMEOUT_CODE;
        } else {
            apiError.setCode(ClientCode.SERVER_TIMEOUT_CODE);
            code = ClientCode.SERVER_TIMEOUT_CODE;
        }
        apiError.setMessage("مشکل برقرار ارتباط با سرور");
        list.add(apiError);

        manageOnError(list, code, requestType);
    }

    private void manageOnError(List<APIError> list, int statusCode, String requestType) {
        try {
            if (listener != null) {
                listener.onError(list, statusCode, requestType);
            } else {
                list.add(new APIError(APIError.EXCEPTION, "خطای غیر منتظره", statusCode));
                listener.onError(list, statusCode, requestType);
            }
        } catch (Exception e) {
            manageInternalException(e);
        }

    }

    private void manageInternalException(Exception ex) {
        ex.printStackTrace();
        List<APIError> list = new ArrayList<>();
        if (listener != null) {
            try {
                list.add(new APIError(APIError.EXCEPTION, "خطای غیر منتظره", ClientCode.EXCEPTION_CODE));
                listener.onError(list, ClientCode.EXCEPTION_CODE, requestType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
