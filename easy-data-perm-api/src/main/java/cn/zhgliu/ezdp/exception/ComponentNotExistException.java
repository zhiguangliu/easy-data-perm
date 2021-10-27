package cn.zhgliu.ezdp.exception;

public class ComponentNotExistException extends RuntimeException {
    public ComponentNotExistException() {
    }

    public ComponentNotExistException(String message) {
        super(message);
    }

    public ComponentNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentNotExistException(Throwable cause) {
        super(cause);
    }

    public ComponentNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
