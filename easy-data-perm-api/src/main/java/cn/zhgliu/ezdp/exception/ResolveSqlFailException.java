package cn.zhgliu.ezdp.exception;

public class ResolveSqlFailException extends RuntimeException {
    public ResolveSqlFailException() {
    }

    public ResolveSqlFailException(String message) {
        super(message);
    }

    public ResolveSqlFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResolveSqlFailException(Throwable cause) {
        super(cause);
    }

    public ResolveSqlFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
