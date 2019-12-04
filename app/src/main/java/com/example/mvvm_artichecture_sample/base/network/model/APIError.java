package com.example.mvvm_artichecture_sample.base.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {

    public static final String FIELD = "field";
    public static final String MESSAGE = "message";
    public static final String EXCEPTION = "exception";

    @SerializedName(FIELD)
    @Expose
    private String field;
    @SerializedName(MESSAGE)
    @Expose
    private String message;

    private int code;

    public APIError(String field, String message, int code) {
        this.field = field;
        this.message = message;
        this.code = code;
    }

    public APIError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public APIError() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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
}
