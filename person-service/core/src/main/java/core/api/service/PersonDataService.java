package core.api.service;

import core.model.PersonData;

public interface PersonDataService {

    PersonData get(Long id);

    PersonData create(PersonData personDataJson);

    PersonData update(Long id, PersonData personDataJson);

    void delete(Long id);

    PersonData assignContact(Long personId, Long contactId);

    PersonData assignMedicalCard(Long personId, Long medicalCardId);

    PersonData assignParent(Long personId, Long parentId);
}
