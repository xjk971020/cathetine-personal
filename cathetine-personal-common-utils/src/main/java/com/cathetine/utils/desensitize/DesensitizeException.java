package com.cathetine.utils.desensitize;

import java.io.Serializable;

public class DesensitizeException extends RuntimeException implements Serializable {
    public DesensitizeException() {
    }

    public DesensitizeException(String message) {
        super(message);
    }

    public DesensitizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DesensitizeException(Throwable cause) {
        super(cause);
    }

    public DesensitizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
