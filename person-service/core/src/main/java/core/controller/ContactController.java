package core.controller;

import core.api.service.ContactService;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private ContactService contactService;

    @GetMapping("/{contact_id}")
    public Contact get(@PathVariable(name="contact_id") Long contactId){
        return Optional.of(contactId)
                .map(contactService::get)
                .orElseThrow();
    }

    @PostMapping()
    public Contact create(@RequestBody Contact contactJson){
        return Optional.ofNullable(contactJson)
                .map(contactService::create)
                .orElseThrow();
    }

    @PutMapping("/{contact_id}/update")
    public Contact update(@PathVariable(name="contact_id") Long contactId, @RequestBody Contact contactJson){
        return Optional.ofNullable(contactJson)
                .map(toUpdate->contactService.update(contactId, contactJson))
                .orElseThrow();
    }

    @DeleteMapping("/{contact_id}/delete")
    public void delete(@PathVariable(name="contact_id") Long contactId){
        contactService.delete(contactId);
    }
}
