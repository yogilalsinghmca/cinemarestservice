package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cinema {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
   private List<Seat> AVAILABLE_SEATS;
    private List<Ticket> PURCHASED_TICKET;

    public Cinema(int total_rows, int total_columns) {
        this.totalRows = total_rows;
        this.totalColumns = total_columns;
        AVAILABLE_SEATS = new ArrayList<>();
        PURCHASED_TICKET = new ArrayList<>();
        Seat seat;
        for (int row = 1; row <= total_rows; row++) {
            for (int column = 1; column <= total_columns; column++) {
                seat = new Seat(row, column);

                AVAILABLE_SEATS.add(seat);
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Seat> getAvailableSeats() {
        return AVAILABLE_SEATS;
    }

    @JsonIgnore
    public List<Ticket> getPurchasedTicket() {
        return PURCHASED_TICKET;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

}
