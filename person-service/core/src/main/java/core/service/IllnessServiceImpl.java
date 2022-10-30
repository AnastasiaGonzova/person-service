package core.service;

import core.api.repository.IllnessRepository;
import core.api.service.IllnessService;
import core.api.service.MedicalCardService;
import core.model.Illness;
import core.model.MedicalCard;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IllnessServiceImpl implements IllnessService {

    @NonNull
    private IllnessRepository illnessRepository;

    @NonNull
    private MedicalCardService medicalCardService;

    @Override
    public Illness get(Long id) {
        return illnessRepository.findById(id).orElseThrow();
    }

    @Override
    public Illness getAndInitialize(Long id) {
        Illness illness = illnessRepository.findById(id).orElseThrow();
        Hibernate.initialize(illness);
        Hibernate.initialize(illness.getMedicalCards());
        return illness;
    }

    @Override
    @Transactional
    public Illness create(Illness illnessJson) {
        return illnessRepository.save(illnessJson);
    }

    @Override
    @Transactional
    public Illness update(Long id, Illness illnessJson) {
        Illness updatedIllness = illnessRepository.findById(id).orElseThrow();
        if(illnessJson.getTypeId()!= null){
            updatedIllness.setTypeId(illnessJson.getTypeId());
        }
        if(illnessJson.getHeaviness() != null){
            updatedIllness.setHeaviness(illnessJson.getHeaviness());
        }
        if(illnessJson.getAppearanceDttm() != null){
            updatedIllness.setAppearanceDttm(illnessJson.getAppearanceDttm());
        }
        if(illnessJson.getRecoveryDt() != null){
            updatedIllness.setRecoveryDt(illnessJson.getRecoveryDt());
        }
        return illnessRepository.save(updatedIllness);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        illnessRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Illness assignMedicalCard(Long medicalCardId, Long illnessId){
        final MedicalCard medicalCard = medicalCardService.get(medicalCardId);
        final Illness illness = illnessRepository.findById(illnessId).orElseThrow();
        illness.assignMedicalCard(medicalCard);
        return illnessRepository.save(illness);
    }
}
