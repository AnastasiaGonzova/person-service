package core.api.service;

import core.model.MedicalCard;

public interface MedicalCardService {
    MedicalCard get(Long id);

    MedicalCard getAndInitialize(Long id);

    MedicalCard create(MedicalCard medicalCardJson);

    MedicalCard update(Long id, MedicalCard medicalCardJson);

    void delete(Long id);

    MedicalCard assignIllness(Long medicalCardId, Long illnessId);
}
