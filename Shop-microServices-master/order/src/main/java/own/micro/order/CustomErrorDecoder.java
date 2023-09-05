package own.micro.order;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new FeignServerException("Проблемы с сервером, просим прощение!");
        } else if (responseStatus.is4xxClientError()) {
            return new FeignClientException("Некорректный запрос");

        } else {
            return new Exception("Другое исключение");
        }

    }
}
