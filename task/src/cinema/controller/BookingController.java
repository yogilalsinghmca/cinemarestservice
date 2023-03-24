package cinema.controller;

import cinema.exception.AuthorizationException;
import cinema.model.*;
import cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {
    private final TicketService ticketService;

    @Autowired
    public BookingController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/seats")
    public Cinema getSeats() {
        return ticketService.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody Seat seat) {
        Ticket ticket = ticketService.purchase(seat);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnTicketRequestDTO> refundTicket(@RequestBody TicketToken token) {

        Ticket returnedTicket = ticketService.refundTicket(token.getToken());

        ReturnTicketRequestDTO returnTicketRequestDTO = new ReturnTicketRequestDTO(returnedTicket.getSeat());

        return new ResponseEntity<>(returnTicketRequestDTO, HttpStatus.OK);
    }

    @PostMapping(value = {"/stats", "/stats/{password}"})
    public ResponseEntity<Statistics> stats(@RequestParam(value = "password", required = false) String password) {
        if(password != null && password.equals("super_secret")) {
            Statistics statistics = ticketService.getStatistics();
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } else {
            throw new AuthorizationException("The password is wrong!");
        }
    }
}
