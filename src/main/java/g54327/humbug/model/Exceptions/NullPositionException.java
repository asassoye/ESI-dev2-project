package g54327.humbug.model.Exceptions;

public class NullPositionException extends IllegalArgumentException {
    public NullPositionException() {
    }

    public NullPositionException(String s) {
        super(s);
    }

    public NullPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPositionException(Throwable cause) {
        super(cause);
    }
}
