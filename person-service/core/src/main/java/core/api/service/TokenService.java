package core.api.service;

import core.model.User;

public interface TokenService {
    String generateToken(User user);

    String extractUsernameAndValidate(String token);
}
