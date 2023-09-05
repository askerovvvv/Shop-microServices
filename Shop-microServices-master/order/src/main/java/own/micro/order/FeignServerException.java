package own.micro.order;

public class FeignServerException extends RuntimeException {
    FeignServerException(String message) {
        super(message);
    }
}
