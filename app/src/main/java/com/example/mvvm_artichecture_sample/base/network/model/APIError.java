package com.example.mvvm_artichecture_sample.base.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {
	
	public static final String FIELD = "field";
	public static final String MESSAGE = "message";
	public static final String EXCEPTION = "exception";
	
	@SerializedName("code")
	@Expose
	private int code;
	@SerializedName("result")
	@Expose
	private String message;
	@SerializedName("error")
	@Expose
	private Error error;
	
	public APIError(String message, int code) {
		this.message = message;
		this.code = code;
	}
	
	public APIError(Integer field, String message) {
		this.message = message;
	}
	
	public APIError() {
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public Error getError() {
		return error;
	}
	
	public void setError(Error error) {
		this.error = error;
	}
}
