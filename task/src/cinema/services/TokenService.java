package cinema.services;

import java.util.UUID;

public class TokenService {
    public static String getNewToken() {
      return  UUID.randomUUID().toString();
    }
}
