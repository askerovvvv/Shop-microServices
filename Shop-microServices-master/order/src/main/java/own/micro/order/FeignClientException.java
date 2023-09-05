package own.micro.order;

public class FeignClientException extends RuntimeException{
    FeignClientException(String message) {
        super(message);
    }
}
