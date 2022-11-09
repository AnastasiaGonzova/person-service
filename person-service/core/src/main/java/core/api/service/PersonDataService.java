package core.api.service;

import core.model.PersonData;

public interface PersonDataService {

    PersonData get(Long id);

    PersonData getAndInitialize(Long id);

    PersonData create(PersonData personDataJson);

    PersonData update(Long id, PersonData personDataJson);

    void delete(Long id);

    PersonData assignParent(Long personId, Long parentId);

    PersonData removeParent(Long personId, Long parentId);
}
