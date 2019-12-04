package com.example.mvvm_artichecture_sample.base.network.baseobserver;

import java.util.List;

public interface WebServiceListener {

    void onSuccess(Object object, int statusCode, String requestType) throws Exception;

    void onError(List object, int statusCode, String requestType) throws Exception;

}
