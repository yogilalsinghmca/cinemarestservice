package cinema.exception;

import cinema.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SeatOutOfBoundsException.class, TicketAlreadyPurchasedException.class})
    public ResponseEntity<CustomErrorResponse> badRequestException(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({AuthorizationException.class})
    public ResponseEntity<CustomErrorResponse> SecurityException(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError(ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);

    }
}
