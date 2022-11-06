package core.api.service;

import core.model.Contact;
import core.model.PersonData;
import core.model.User;
import dto.internal.LoginRequest;

public interface SecurityService {

    String login(LoginRequest loginRequest);
    String sighUp(PersonData personDataJson, User userJson, Contact contact);
}
