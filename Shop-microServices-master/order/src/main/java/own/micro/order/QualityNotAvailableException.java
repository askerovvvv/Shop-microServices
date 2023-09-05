package own.micro.order;

public class QualityNotAvailableException extends RuntimeException{
    QualityNotAvailableException(String message) {
        super(message);
    }
}
