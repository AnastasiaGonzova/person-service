package core.controller;

import core.api.service.MedicalCardService;
import core.model.MedicalCard;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MedicalCardController {

    @NonNull
    private MedicalCardService medicalCardService;

    @GetMapping("/medical_cards/{med_card_id}")
    public MedicalCard get(@PathVariable(name="med_card_id") Long medicalCardId){
        return Optional.of(medicalCardId)
                .map(medicalCardService::getAndInitialize)
                .orElseThrow();
    }

    @PostMapping("/medical_card")
    public MedicalCard create(@RequestBody MedicalCard medicalCardJson){
        return Optional.ofNullable(medicalCardJson)
                .map(medicalCardService::create)
                .orElseThrow();
    }

    @PutMapping("/medical_card/{med_card_id}/update")
    public MedicalCard update(@PathVariable(name="med_card_id") Long medicalCardId, @RequestBody MedicalCard medicalCardJson){
        return Optional.ofNullable(medicalCardJson)
                .map(toUpdate->medicalCardService.update(medicalCardId, medicalCardJson))
                .orElseThrow();
    }

    @DeleteMapping("/medical_card/{med_card_id}/delete")
    public void delete(@PathVariable(name="med_card_id") Long medicalCardId){
        medicalCardService.delete(medicalCardId);
    }
}
