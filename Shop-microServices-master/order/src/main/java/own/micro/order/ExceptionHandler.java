package own.micro.order;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(QualityNotAvailableException.class)
    public ResponseEntity<?> handleException(
            QualityNotAvailableException exception,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
//
    @org.springframework.web.bind.annotation.ExceptionHandler(FeignServerException.class)
    public ResponseEntity<?> handleException(
            FeignServerException exception,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
            request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        log.error("Ошибка СЕРВЕРА при отправке данных на другой микросервис {}", request.getRequestURL());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FeignClientException.class)
    public ResponseEntity<?> handleException(
            FeignClientException exception,
            HttpServletRequest request
    ) {
        ApiError apiError = new ApiError(
            request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        log.error("Ошибка клиента при отправке данных на другой микросервис {}", request.getRequestURL());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
