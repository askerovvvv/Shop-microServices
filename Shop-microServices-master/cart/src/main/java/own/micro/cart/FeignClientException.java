package own.micro.cart;

public class FeignClientException extends RuntimeException{
    FeignClientException(String message) {
        super(message);
    }
}
