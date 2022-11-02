package core.controller;

import core.api.service.MedicalCardService;
import core.model.MedicalCard;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/medical_cards")
public class MedicalCardController {

    private MedicalCardService medicalCardService;

    @GetMapping("/{med_card_id}")
    public MedicalCard get(@PathVariable(name="med_card_id") Long medicalCardId){
        return Optional.of(medicalCardId)
                .map(medicalCardService::getAndInitialize)
                .orElseThrow();
    }

    @PostMapping()
    public MedicalCard create(@RequestBody MedicalCard medicalCardJson){
        return Optional.ofNullable(medicalCardJson)
                .map(medicalCardService::create)
                .orElseThrow();
    }

    @PutMapping("/{med_card_id}/update")
    public MedicalCard update(@PathVariable(name="med_card_id") Long medicalCardId, @RequestBody MedicalCard medicalCardJson){
        return Optional.ofNullable(medicalCardJson)
                .map(toUpdate->medicalCardService.update(medicalCardId, medicalCardJson))
                .orElseThrow();
    }

    @DeleteMapping("/{med_card_id}/delete")
    public void delete(@PathVariable(name="med_card_id") Long medicalCardId){
        medicalCardService.delete(medicalCardId);
    }
}
