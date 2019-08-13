package com.bokecc.cloud.wowza.exception;

public class InvalidException extends Exception {

    private static final long serialVersionUID = 448865329272002974L;

    public InvalidException() {
        super();
    }

    public InvalidException(String msg) {
        super(msg);
    }
}
