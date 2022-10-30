package core.service;

import core.api.repository.MedicalCardRepository;
import core.api.service.MedicalCardService;
import core.model.MedicalCard;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MedicalCardServiceImpl implements MedicalCardService {

    @NonNull
    private MedicalCardRepository medicalCardRepository;

    @Override
    public MedicalCard get(Long id) {
        return medicalCardRepository.findById(id).orElseThrow();
    }

    @Override
    public MedicalCard getAndInitialize(Long id) {
        MedicalCard medicalCard = medicalCardRepository.findById(id).orElseThrow();
        Hibernate.initialize(medicalCard);
        return medicalCard;
    }

    @Override
    @Transactional
    public MedicalCard create(MedicalCard medicalCardJson) {
        return medicalCardRepository.save(medicalCardJson);
    }

    @Override
    @Transactional
    public MedicalCard update(Long id, MedicalCard medicalCardJson) {
        MedicalCard updatedMedicalCard = medicalCardRepository.findById(id).orElseThrow();
        if(medicalCardJson.getClientStatus()!= null){
            updatedMedicalCard.setClientStatus(medicalCardJson.getClientStatus());
        }
        if(medicalCardJson.getMedStatus() != null){
            updatedMedicalCard.setMedStatus(medicalCardJson.getMedStatus());
        }
        if(medicalCardJson.getRegistryDt() != null){
            updatedMedicalCard.setRegistryDt(medicalCardJson.getRegistryDt());
        }
        if(medicalCardJson.getCommentAbout() != null){
            updatedMedicalCard.setCommentAbout(medicalCardJson.getCommentAbout());
        }
        return medicalCardRepository.save(updatedMedicalCard);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        medicalCardRepository.deleteById(id);
    }
}
