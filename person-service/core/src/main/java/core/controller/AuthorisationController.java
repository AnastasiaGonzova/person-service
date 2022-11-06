package core.controller;

import core.api.service.SecurityService;
import core.model.Contact;
import core.model.PersonData;
import core.model.User;
import dto.internal.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthorisationController {

    private SecurityService securityService;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        securityService.login(loginRequest);
        return bindingResult.hasErrors();
    }

    @PostMapping("/sign-up")
    public boolean signUp(@RequestBody User user, @RequestBody PersonData personData, @RequestBody Contact contact, BindingResult bindingResult){
        securityService.sighUp(personData, user, contact);
        return bindingResult.hasErrors();
    }
}
