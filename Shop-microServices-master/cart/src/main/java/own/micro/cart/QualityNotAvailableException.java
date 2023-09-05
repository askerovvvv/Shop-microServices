package own.micro.cart;

public class QualityNotAvailableException extends RuntimeException{
    QualityNotAvailableException(String message) {
        super(message);
    }
}
