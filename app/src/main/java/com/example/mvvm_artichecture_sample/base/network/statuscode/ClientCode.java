package com.example.mvvm_artichecture_sample.base.network.statuscode;

public class ClientCode {

    public static final int NO_INTERNET_TIMEOUT_CODE = 700;
    public static final int SERVER_TIMEOUT_CODE = 701;

    public static final int EXCEPTION_CODE = -1;


    public static boolean isExceptionError(int code){
        return (code ==NO_INTERNET_TIMEOUT_CODE||code==SERVER_TIMEOUT_CODE);
    }
}
