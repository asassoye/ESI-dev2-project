package g54327.humbug.model.Exceptions;

public class LevelNotStartedException extends RuntimeException {
    public LevelNotStartedException() {
    }

    public LevelNotStartedException(String message) {
        super(message);
    }

    public LevelNotStartedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LevelNotStartedException(Throwable cause) {
        super(cause);
    }

    public LevelNotStartedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
