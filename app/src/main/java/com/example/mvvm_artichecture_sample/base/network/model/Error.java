package com.example.mvvm_artichecture_sample.base.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {
	@SerializedName("reason")
	@Expose
	private Integer reason;
	@SerializedName("message")
	@Expose
	private String message;
	
	public Integer getReason() {
		return reason;
	}
	
	public void setReason(Integer reason) {
		this.reason = reason;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
