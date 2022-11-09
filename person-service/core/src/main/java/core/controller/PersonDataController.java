package core.controller;

import core.api.service.PersonDataService;
import core.model.PersonData;
import dto.external.PersonDataDto;
import dto.internal.PersonDataInternalDto;
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
                .map(personDataService::getAndInitialize)
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @PutMapping("/{person_data_id}/update")
    public PersonDataDto update(@PathVariable(name="person_data_id") Long personId,
                                @RequestBody PersonDataInternalDto personDataJson){
        return Optional.ofNullable(personDataJson)
                .map(current->modelMapper.map(current, PersonData.class))
                .map(toUpdate->personDataService.update(personId, toUpdate))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{person_data_id}/admin/delete")
    public void delete(@PathVariable(name="person_data_id") Long personId){
        personDataService.delete(personId);
    }

    @PostMapping("/{person_data_id}/admin/parents/{parent_id}")
    public PersonDataDto assignParent(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="parent_id") Long parentId){
        return Optional.of(personId)
                .map(current->personDataService.assignParent(current, parentId))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{person_data_id}/admin/parents/{parent_id}/delete")
    public PersonDataDto removeParent(@PathVariable(name="person_data_id") Long personId, @PathVariable(name="parent_id") Long parentId){
        return Optional.of(personId)
                .map(current->personDataService.removeParent(current, parentId))
                .map(current->modelMapper.map(current, PersonDataDto.class))
                .orElseThrow();
    }
}
