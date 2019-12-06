package com.example.mvvm_artichecture_sample.base.network.baseobserver;

import com.example.mvvm_artichecture_sample.base.network.model.APIError;
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode;
import com.example.mvvm_artichecture_sample.base.network.statuscode.RestCode;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class SuccessConsumer implements Consumer<Response> {
	
	private WeakReference<WebServiceListener> listenerWeakReference;
	private String requestType;
	
	public SuccessConsumer(WebServiceListener listener, String requestType) {
		listenerWeakReference = new WeakReference<>(listener);
		this.requestType = requestType;
	}
	
	@Override
	public void accept(Response tResponse) throws Exception {
		if (tResponse.isSuccessful()) {
			manageSuccess(tResponse);
		} else {
			if (tResponse.errorBody() != null && tResponse.code() < RestCode.SERVER_ERROR_STATUS_CODE) {
				APIError apiErrors = getErrorMessage(tResponse.errorBody());
				if (apiErrors != null) {
					manageOnError(apiErrors, tResponse.code(), requestType);
				} else {
					apiErrors = new APIError("Unexpected error", tResponse.code());
					manageOnError(apiErrors, tResponse.code(), requestType);
				}
				
			} else {
				APIError apiError = new APIError("Unexpected error", tResponse.code());
				manageOnError(apiError, tResponse.code(), requestType);
			}
		}
	}
	
	private void manageSuccess(Response tResponse) {
		if (listenerWeakReference.get() != null) {
			try {
				listenerWeakReference.get().onSuccess(tResponse.body(), tResponse.code(), requestType);
			} catch (Exception e) {
				manageInternalException(e);
			}
		}
	}
	
	private void manageOnError(APIError apiError, int statusCode, String requestType) {
		try {
			if (listenerWeakReference.get() != null) {
				listenerWeakReference.get().onError(apiError, statusCode, requestType);
			} else {
				apiError = new APIError("Unexpected error", statusCode);
				listenerWeakReference.get().onError(apiError, statusCode, requestType);
			}
		} catch (Exception e) {
			manageInternalException(e);
		}
		
	}
	
	private void manageInternalException(Exception ex) {
		ex.printStackTrace();
		if (listenerWeakReference.get() != null) {
			try {
				APIError apiError = new APIError("Unexpected error", ClientCode.EXCEPTION_CODE);
				listenerWeakReference.get().onError(apiError, ClientCode.EXCEPTION_CODE, requestType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private APIError getErrorMessage(ResponseBody responseBody) {
		APIError apiErrorList = new APIError();
		try {
			apiErrorList = new Gson().fromJson(responseBody.string(), APIError.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiErrorList;
	}
	
	
}
