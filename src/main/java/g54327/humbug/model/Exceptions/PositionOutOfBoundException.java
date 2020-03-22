package g54327.humbug.model.Exceptions;

public class PositionOutOfBoundException extends IllegalArgumentException {
    public PositionOutOfBoundException() {
    }

    public PositionOutOfBoundException(String s) {
        super(s);
    }

    public PositionOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PositionOutOfBoundException(Throwable cause) {
        super(cause);
    }
}
