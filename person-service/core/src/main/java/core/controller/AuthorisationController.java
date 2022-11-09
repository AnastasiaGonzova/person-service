package core.controller;

import core.api.service.SecurityService;
import dto.internal.LoginRequest;
import dto.internal.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@AllArgsConstructor
public class AuthorisationController {

    private SecurityService securityService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return securityService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody SignUpRequest signUpRequest){
        return securityService.sighUp(signUpRequest);
    }
}
