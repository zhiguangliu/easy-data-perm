package cn.zhgliu.ezdp.exception;

public class DataPermRuleFetchException extends RuntimeException {
    public DataPermRuleFetchException() {
    }

    public DataPermRuleFetchException(String message) {
        super(message);
    }

    public DataPermRuleFetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataPermRuleFetchException(Throwable cause) {
        super(cause);
    }

    public DataPermRuleFetchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
