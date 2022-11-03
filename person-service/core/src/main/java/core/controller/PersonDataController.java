package core.controller;

import core.api.service.PersonDataService;
import core.model.PersonData;
import dto.external.PersonDataDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonDataController {

    private PersonDataService personDataService;

    private ModelMapper modelMapper;

    @GetMapping("/{person_data_id}")
    public PersonDataDto get(@PathVariable(name="person_data_id") Long personId){
        return Optional.of(personId)
                .map(personDataService::get)
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @PostMapping()
    public PersonDataDto create(@RequestBody PersonData personDataJson){
        return Optional.ofNullable(personDataJson)
                .map(personDataService::create)
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @PutMapping("/{person_data_id}/update")
    public PersonDataDto update(@PathVariable(name="person_data_id") Long personId, @RequestBody PersonData personDataJson){
        return Optional.ofNullable(personDataJson)
                .map(toUpdate->personDataService.update(personId, personDataJson))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{person_data_id}/delete")
    public void delete(@PathVariable(name="person_data_id") Long personId){
        personDataService.delete(personId);
    }

    @PostMapping("/{person_data_id}/contacts/{contact_id}")
    public PersonDataDto assignContact(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="contact_id") Long contactId){
        return Optional.of(personId)
                .map(current->personDataService.assignContact(personId, contactId))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @PostMapping("/{person_data_id}/medical_cards/{medical_card_id}")
    public PersonDataDto assignMedicalCard(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="medical_card_id") Long medicalCardId){
        return Optional.of(personId)
                .map(current->personDataService.assignMedicalCard(personId, medicalCardId))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @PostMapping("/{person_data_id}/parents/{parent_id}")
    public PersonDataDto assignParent(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="parent_id") Long parentId){
        return Optional.of(personId)
                .map(current->personDataService.assignParent(personId, parentId))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }
}
