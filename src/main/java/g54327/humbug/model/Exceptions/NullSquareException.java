package g54327.humbug.model.Exceptions;

public class NullSquareException extends IllegalArgumentException {
    public NullSquareException() {
    }

    public NullSquareException(String s) {
        super(s);
    }

    public NullSquareException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullSquareException(Throwable cause) {
        super(cause);
    }
}
