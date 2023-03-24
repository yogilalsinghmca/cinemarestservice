package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The ticket has been already purchased!")
public class TicketAlreadyPurchasedException extends RuntimeException {
    public TicketAlreadyPurchasedException(String s) {
        super(s);
    }
}
