package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TicketToken {
    private UUID token;

    public TicketToken(@JsonProperty("token") UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}
