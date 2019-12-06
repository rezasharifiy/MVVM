package com.example.mvvm_artichecture_sample.base.network.baseobserver;

import com.example.mvvm_artichecture_sample.base.network.model.APIError;

public interface WebServiceListener {
	
	void onSuccess(Object object, int statusCode, String requestType) throws Exception;
	
	void onError(APIError apiError, int statusCode, String requestType) throws Exception;
	
}
