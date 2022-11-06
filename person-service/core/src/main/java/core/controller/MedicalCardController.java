package core.controller;

import core.api.service.MedicalCardService;
import core.model.MedicalCard;
import dto.external.MedicalCardDto;
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
    public MedicalCardDto update(@PathVariable(name="med_card_id") Long medicalCardId, @RequestBody MedicalCard medicalCardJson){
        return Optional.ofNullable(medicalCardJson)
                .map(toUpdate->medicalCardService.update(medicalCardId, medicalCardJson))
                .map(current->modelMapper.map(current, MedicalCardDto.class))
                .orElseThrow();
    }

    @DeleteMapping("/{med_card_id}/admin/delete")
    public void delete(@PathVariable(name="med_card_id") Long medicalCardId){
        medicalCardService.delete(medicalCardId);
    }
}
