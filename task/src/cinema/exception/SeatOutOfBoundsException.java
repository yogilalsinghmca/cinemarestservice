package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SeatOutOfBoundsException extends RuntimeException {
    public SeatOutOfBoundsException(String reason) {
        super(reason);
    }
}
