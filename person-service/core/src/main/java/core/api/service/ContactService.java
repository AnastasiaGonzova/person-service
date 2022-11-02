package core.api.service;

import core.model.Contact;

public interface ContactService {

    Contact get(Long id);

    Contact create(Contact contactJson);

    Contact update(Long id, Contact contactJson);

    void delete(Long id);
}
