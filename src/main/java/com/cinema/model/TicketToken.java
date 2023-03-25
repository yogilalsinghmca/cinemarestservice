package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record TicketToken(UUID token) {
    public TicketToken(@JsonProperty("token") UUID token) {
        this.token = token;
    }
}
