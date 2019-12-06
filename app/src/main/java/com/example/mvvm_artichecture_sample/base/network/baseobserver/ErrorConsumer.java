package com.example.mvvm_artichecture_sample.base.network.baseobserver;

import com.example.mvvm_artichecture_sample.base.network.model.APIError;
import com.example.mvvm_artichecture_sample.base.network.statuscode.ClientCode;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

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
		APIError apiError = new APIError();
		int code;
		if (throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
			apiError.setCode(ClientCode.NO_INTERNET_TIMEOUT_CODE);
			code = ClientCode.NO_INTERNET_TIMEOUT_CODE;
		} else {
			apiError.setCode(ClientCode.SERVER_TIMEOUT_CODE);
			code = ClientCode.SERVER_TIMEOUT_CODE;
		}
		apiError.setMessage("Unexpected error");
		manageOnError(apiError, code, requestType);
	}
	
	private void manageOnError(APIError apiError, int statusCode, String requestType) {
		try {
			if (listener != null) {
				listener.onError(apiError, statusCode, requestType);
			} else {
				apiError = new APIError("Unexpected error", statusCode);
				listener.onError(apiError, statusCode, requestType);
			}
		} catch (Exception e) {
			manageInternalException(e);
		}
		
	}
	
	private void manageInternalException(Exception ex) {
		ex.printStackTrace();
		if (listener != null) {
			try {
				APIError apiError = new APIError("Unexpected error", ClientCode.EXCEPTION_CODE);
				listener.onError(apiError, ClientCode.EXCEPTION_CODE, requestType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
