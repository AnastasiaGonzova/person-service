package core.controller;

import core.api.service.IllnessService;
import core.model.Illness;
import dto.external.IllnessDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/illnesses")
public class IllnessController {

    private IllnessService illnessService;
    private ModelMapper modelMapper;

    @GetMapping("/{illness_id}")
    public IllnessDto get(@PathVariable(name="illness_id") Long illnessId){
        return Optional.of(illnessId)
                .map(illnessService::getAndInitialize)
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }

    @PostMapping()
    public IllnessDto create(@RequestBody Illness illnessJson){
        return Optional.ofNullable(illnessJson)
                .map(illnessService::create)
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }

    @PutMapping("/{illness_id}/update")
    public IllnessDto update(@PathVariable(name="illness_id") Long illnessId, @RequestBody Illness illnessJson){
        return Optional.ofNullable(illnessJson)
                .map(toUpdate->illnessService.update(illnessId, illnessJson))
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{illness_id}/delete")
    public void delete(@PathVariable(name="illness_id") Long illnessId){
        illnessService.delete(illnessId);
    }

    @PostMapping("/{illness_id}/medical_cards/{medical_card_id}")
    public IllnessDto assignMedicalCard(@PathVariable(name="illness_id") Long illnessId, @PathVariable(name="medical_card_id") Long medicalCardId){
        return Optional.of(illnessId)
                .map(current->illnessService.assignMedicalCard(medicalCardId, illnessId))
                .map(current->modelMapper.map(current, IllnessDto.class))
                .orElseThrow();
    }
}
