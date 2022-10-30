package core.api.service;

import core.model.Illness;

public interface IllnessService {

    Illness get(Long id);

    Illness getAndInitialize(Long id);

    Illness create(Illness illnessJson);

    Illness update(Long id, Illness illnessJson);

    void delete(Long id);

    Illness assignMedicalCard(Long medicalCardId, Long illnessId);
}
