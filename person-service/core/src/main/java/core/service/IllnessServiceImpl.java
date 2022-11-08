package core.service;

import core.api.repository.IllnessRepository;
import core.api.service.IllnessService;
import core.api.service.MedicalCardService;
import core.model.Illness;
import core.model.MedicalCard;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class IllnessServiceImpl implements IllnessService {

    private IllnessRepository illnessRepository;
    private MedicalCardService medicalCardService;

    private ModelMapper modelMapper;

    @Override
    public Illness get(Long id) {
        return illnessRepository.getById(id);
    }

    @Override
    public Illness getAndInitialize(Long id) {
        Illness illness = illnessRepository.getById(id);
        Hibernate.initialize(illness);
        Hibernate.initialize(illness.getMedicalCard());
        return illness;
    }

    @Override
    @Transactional
    public Illness create(Illness illnessJson) {
        return illnessRepository.saveAndFlush(illnessJson);
    }

    @Override
    @Transactional
    public Illness update(Long id, Illness illnessJson) {
        Illness updatedIllness = illnessRepository.getById(id);
        modelMapper.map(illnessJson, updatedIllness);
        return illnessRepository.saveAndFlush(updatedIllness);
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
        //illness.assignMedicalCard(medicalCard);
        return illnessRepository.save(illness);
    }
}
