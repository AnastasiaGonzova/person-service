package core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstPageController {

    @GetMapping()
    public String greeting(){
        return "Welcome!";
    }
}
