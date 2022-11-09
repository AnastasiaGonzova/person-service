package core.api.service;

import core.model.Address;

public interface AddressService {
    Address get(Long id);

    Address getAndInitialize(Long id);

    Address create(Address addressJson);

    Address update(Long id, Address addressJson);

    void delete(Long id);

    Address assignContact(Long addressId, Long contactId);

    Address removeContact(Long addressId, Long contactId);
}
