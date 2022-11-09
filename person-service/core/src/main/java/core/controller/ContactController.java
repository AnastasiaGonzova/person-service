package core.controller;

import core.api.service.ContactService;
import core.model.Contact;
import dto.external.ContactDto;
import dto.internal.ContactInternalDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private ContactService contactService;
    private ModelMapper modelMapper;

    @GetMapping("/{contact_id}")
    public ContactDto get(@PathVariable(name="contact_id") Long contactId){
        return Optional.of(contactId)
                .map(contactService::getAndInitialize)
                .map(current->modelMapper.map(current, ContactDto.class))
                .orElseThrow();
    }

    @PutMapping("/{contact_id}/update")
    public ContactDto update(@PathVariable(name="contact_id") Long contactId, @RequestBody ContactInternalDto contactJson){
        return Optional.ofNullable(contactJson)
                .map(current->modelMapper.map(current, Contact.class))
                .map(toUpdate->contactService.update(contactId, toUpdate))
                .map(current->modelMapper.map(current, ContactDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{contact_id}/admin/delete")
    public void delete(@PathVariable(name="contact_id") Long contactId){
        contactService.delete(contactId);
    }
}
