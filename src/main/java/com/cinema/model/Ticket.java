package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private Seat seat;
    private UUID token;

    public Ticket(Seat seat) {
        this.seat = seat;
        this.token = UUID.randomUUID();
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return token.equals(ticket.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
