package com.cathetine.utils.type;

/**
 * 枚举类型加载异常
 */
public class IllegalEnumClassException extends RuntimeException{
    public IllegalEnumClassException() {
    }

    public IllegalEnumClassException(String message) {
        super(message);
    }

    public IllegalEnumClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEnumClassException(Throwable cause) {
        super(cause);
    }

    public IllegalEnumClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
