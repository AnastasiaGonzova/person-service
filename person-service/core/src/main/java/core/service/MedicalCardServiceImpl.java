package core.service;

import core.api.repository.MedicalCardRepository;
import core.api.service.MedicalCardService;
import core.model.MedicalCard;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MedicalCardServiceImpl implements MedicalCardService {

    private MedicalCardRepository medicalCardRepository;

    private ModelMapper modelMapper;

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
        modelMapper.map(medicalCardJson, updatedMedicalCard);
        return medicalCardRepository.save(updatedMedicalCard);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        medicalCardRepository.deleteById(id);
    }
}
