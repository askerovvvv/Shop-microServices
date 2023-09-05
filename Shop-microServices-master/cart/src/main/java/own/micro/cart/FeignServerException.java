package own.micro.cart;

public class FeignServerException extends RuntimeException {
    FeignServerException(String message) {
        super(message);
    }
}
