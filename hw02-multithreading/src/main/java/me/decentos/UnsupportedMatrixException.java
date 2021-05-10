package me.decentos;

public class UnsupportedMatrixException extends RuntimeException {

    public UnsupportedMatrixException() {
    }

    public UnsupportedMatrixException(String message) {
        super(message);
    }

    public UnsupportedMatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedMatrixException(Throwable cause) {
        super(cause);
    }

    public UnsupportedMatrixException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
