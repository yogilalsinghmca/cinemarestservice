package com.cinema.services;


import com.cinema.exception.SeatOutOfBoundsException;
import com.cinema.exception.TicketAlreadyPurchasedException;
import com.cinema.model.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final Cinema cinema = new Cinema(Constant.MAX_ROW, Constant.MAX_COLUMN);

    public Cinema getSeats() {
        return cinema;
    }

    public Ticket purchase(Seat seat) {
        if (seat.getRow() > Constant.MAX_ROW || seat.getRow() < 0
                || seat.getColumn() > Constant.MAX_COLUMN || seat.getColumn() < 0) {
            throw new SeatOutOfBoundsException("The number of a row or a column is out of bounds!");
        }
        Optional<Seat> expectedSeatOpt = findSeat(seat);

        if (!(expectedSeatOpt.isPresent() && expectedSeatOpt.get().isFree())) {
            throw new TicketAlreadyPurchasedException("The ticket has been already purchased!");
        }
        Seat expectedSeat = expectedSeatOpt.get();
        // mark seat as sold
        expectedSeat.setFree(false);

        Ticket ticket = new Ticket(expectedSeat);
        this.cinema.getPurchasedTicket().add(ticket);

        return ticket;
    }

    public Ticket refundTicket(UUID token) {
        Ticket ticket = searchTicket(token);
        cinema.getPurchasedTicket().remove(ticket);

        Optional<Seat> returnedSeat = cinema.getAvailableSeats().stream().filter(
                seat -> seat.getRow() == ticket.getSeat().getRow() &&
                        seat.getColumn() == ticket.getSeat().getColumn()).findFirst();

        returnedSeat.get().setFree(true);

        return ticket;
    }

    public Optional<Seat> findSeat(Seat seat) {

        List<Seat> seats = cinema.getAvailableSeats();

        return seats.stream().filter(s -> s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()).findFirst();
    }

    public Ticket searchTicket(UUID token) {
        Optional<Ticket> seatOpt = cinema.getPurchasedTicket().stream().filter(s -> s.getToken().equals(token)).findFirst();

        if (seatOpt.isPresent()) {
            return seatOpt.get();
        } else {
            throw new SeatOutOfBoundsException("Wrong token!");
        }
    }

    public Statistics getStatistics() {
        int currentIncome = cinema.getPurchasedTicket().stream().mapToInt(ticket -> ticket.getSeat().getPrice()).sum();
        long numberOfAvailableSeats = cinema.getAvailableSeats().stream().filter(Seat::isFree).count();
        int numberOfPurchasedTickets = cinema.getPurchasedTicket().size();

        return new Statistics(currentIncome, numberOfAvailableSeats, numberOfPurchasedTickets);
    }
}