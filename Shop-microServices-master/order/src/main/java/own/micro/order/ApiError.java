package own.micro.order;

import java.time.LocalDateTime;

public record ApiError(
    String path,
    String message,
    int status,
    LocalDateTime localDateTime
) {
}
