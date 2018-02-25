package com.pax.common.exception;

/**
 * com.pax.core.exception
 * Created by yyyty on 2017/2/24.
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 2148374270769534531L;

    public SystemException() {
        super();
    }

    public SystemException(String message) {

        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
