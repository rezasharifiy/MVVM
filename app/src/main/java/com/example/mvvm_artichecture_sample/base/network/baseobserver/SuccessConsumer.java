package com.example.mvvm_artichecture_sample.base.network.baseobserver;

import com.example.mvvm_artichecture_sample.base.network.model.APIError;
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode;
import com.example.mvvm_artichecture_sample.base.network.statuscode.RestCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class SuccessConsumer implements Consumer<Response> {

    private WebServiceListener listener;
    private String requestType;

    public SuccessConsumer(WebServiceListener listener, String requestType) {
        this.listener = listener;
        this.requestType = requestType;
    }

    @Override
    public void accept(Response tResponse) throws Exception {
        if (tResponse.isSuccessful()) {
            manageSuccess(tResponse);
        } else {
            if (tResponse.errorBody() != null && tResponse.code() < RestCode.SERVER_ERROR_STATUS_CODE) {
                List<APIError> apiErrors = getErrorMessage(tResponse.errorBody());
                if (apiErrors.size() != 0) {
                    manageOnError(apiErrors, tResponse.code(), requestType);
                } else {
                    apiErrors.add(new APIError("", "خطای غیر منتظره", tResponse.code()));
                    manageOnError(apiErrors, tResponse.code(), requestType);
                }

            } else {
                List<APIError> list = new ArrayList<>();
                list.add(new APIError(APIError.EXCEPTION, "خطای غیر منتظره", tResponse.code()));
                manageOnError(list, tResponse.code(), requestType);
            }
        }
    }

    private void manageSuccess(Response tResponse) {
        if (listener != null) {
            try {
                listener.onSuccess(tResponse.body(), tResponse.code(), requestType);
            } catch (Exception e) {
                manageInternalException(e);
            }
        }
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

    private List<APIError> getErrorMessage(ResponseBody responseBody) {
        Type listType = new TypeToken<ArrayList<APIError>>() {
        }.getType();
        List<APIError> apiErrorList = new ArrayList<>();
        try {
            apiErrorList = new Gson().fromJson(responseBody.string(), listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiErrorList;
    }


}
