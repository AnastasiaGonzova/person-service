package core.controller;

import core.api.service.IllnessService;
import core.model.Illness;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/illnesses")
public class IllnessController {

    private IllnessService illnessService;

    @GetMapping("/{illness_id}")
    public Illness get(@PathVariable(name="illness_id") Long illnessId){
        return Optional.of(illnessId)
                .map(illnessService::getAndInitialize)
                .orElseThrow();
    }

    @PostMapping()
    public Illness create(@RequestBody Illness illnessJson){
        return Optional.ofNullable(illnessJson)
                .map(illnessService::create)
                .orElseThrow();
    }

    @PutMapping("/{illness_id}/update")
    public Illness update(@PathVariable(name="illness_id") Long illnessId, @RequestBody Illness illnessJson){
        return Optional.ofNullable(illnessJson)
                .map(toUpdate->illnessService.update(illnessId, illnessJson))
                .orElseThrow();
    }

    @DeleteMapping("/{illness_id}/delete")
    public void delete(@PathVariable(name="illness_id") Long illnessId){
        illnessService.delete(illnessId);
    }

    @PostMapping("/{illness_id}/medical_cards/{medical_card_id}")
    public Illness assignMedicalCard(@PathVariable(name="illness_id") Long illnessId, @PathVariable(name="medical_card_id") Long medicalCardId){
        return Optional.of(illnessId)
                .map(current->illnessService.assignMedicalCard(medicalCardId, illnessId))
                .orElseThrow();
    }
}
