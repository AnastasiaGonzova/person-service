package core.controller;

import core.api.service.MedicalCardService;
import core.model.MedicalCard;
import dto.external.MedicalCardDto;
import dto.internal.MedicalCardInternalDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/medical_cards")
public class MedicalCardController {

    private MedicalCardService medicalCardService;
    private ModelMapper modelMapper;

    @GetMapping("/{med_card_id}")
    public MedicalCardDto get(@PathVariable(name="med_card_id") Long medicalCardId){
        return Optional.of(medicalCardId)
                .map(medicalCardService::getAndInitialize)
                .map(current->modelMapper.map(current, MedicalCardDto.class))
                .orElseThrow();
    }

    @PutMapping("/{med_card_id}/update")
    public MedicalCardDto update(@PathVariable(name="med_card_id") Long medicalCardId,
                                 @RequestBody MedicalCardInternalDto medicalCardJson){
        return Optional.ofNullable(medicalCardJson)
                .map(current->modelMapper.map(current, MedicalCard.class))
                .map(toUpdate->medicalCardService.update(medicalCardId, toUpdate))
                .map(current->modelMapper.map(current, MedicalCardDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{med_card_id}/admin/delete")
    public void delete(@PathVariable(name="med_card_id") Long medicalCardId){
        medicalCardService.delete(medicalCardId);
    }

    @PostMapping("/{med_card_id}/admin/ilnesses/{illness_id}")
    public MedicalCardDto assignIllness(@PathVariable(name="med_card_id") Long medicalCardId, @PathVariable(name="illness_id") Long illnessId){
        return Optional.of(medicalCardId)
                .map(current->medicalCardService.assignIllness(current, illnessId))
                .map(current->modelMapper.map(current, MedicalCardDto.class))
                .orElseThrow();
    }
}
