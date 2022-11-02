package core.controller;

import core.api.service.PersonDataService;
import core.model.PersonData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonDataController {

    private PersonDataService personDataService;

    @GetMapping("/{person_data_id}")
    public PersonData get(@PathVariable(name="person_data_id") Long personId){
        return Optional.of(personId)
                .map(personDataService::get)
                .orElseThrow();
    }

    @PostMapping()
    public PersonData create(@RequestBody PersonData personDataJson){
        return Optional.ofNullable(personDataJson)
                .map(personDataService::create)
                .orElseThrow();
    }

    @PutMapping("/{person_data_id}/update")
    public PersonData update(@PathVariable(name="person_data_id") Long personId, @RequestBody PersonData personDataJson){
        return Optional.ofNullable(personDataJson)
                .map(toUpdate->personDataService.update(personId, personDataJson))
                .orElseThrow();
    }

    @DeleteMapping("/{person_data_id}/delete")
    public void delete(@PathVariable(name="person_data_id") Long personId){
        personDataService.delete(personId);
    }

    @PostMapping("/{person_data_id}/contacts/{contact_id}")
    public PersonData assignContact(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="contact_id") Long contactId){
        return Optional.of(personId)
                .map(current->personDataService.assignContact(personId, contactId))
                .orElseThrow();
    }

    @PostMapping("/{person_data_id}/medical_cards/{medical_card_id}")
    public PersonData assignMedicalCard(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="medical_card_id") Long medicalCardId){
        return Optional.of(personId)
                .map(current->personDataService.assignMedicalCard(personId, medicalCardId))
                .orElseThrow();
    }

    @PostMapping("/{person_data_id}/parents/{parent_id}")
    public PersonData assignParent(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="parent_id") Long parentId){
        return Optional.of(personId)
                .map(current->personDataService.assignParent(personId, parentId))
                .orElseThrow();
    }
}
