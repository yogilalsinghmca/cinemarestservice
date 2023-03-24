package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnTicketRequestDTO {

    @JsonProperty("returned_ticket")
    private Seat seat;


    public ReturnTicketRequestDTO(Seat seat) {
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }
}
