package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Statistics {
    @JsonProperty("current_income")
    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private long numberOfAvailableSeat;
    @JsonProperty("number_of_purchased_tickets")
    private long numberOfPurchasedTicket;
}
