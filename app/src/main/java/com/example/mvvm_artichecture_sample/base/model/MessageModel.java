package com.example.mvvm_artichecture_sample.base.model;

public class MessageModel {
    private String title;
    private String message;
    private boolean show;

    public MessageModel() {
    }

    public MessageModel(Boolean show,String title, String message) {
        this.title = title;
        this.message = message;
        this.show=show;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
