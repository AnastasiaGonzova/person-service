package core.service;

import core.api.repository.AddressRepository;
import core.api.service.AddressService;
import core.api.service.ContactService;
import core.model.Address;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private ContactService contactService;
    private ModelMapper modelMapper;

    @Override
    public Address get(Long id) {
        return addressRepository.getById(id);
    }

    @Override
    public Address getAndInitialize(Long id) {
        Address address = addressRepository.getById(id);
        Hibernate.initialize(address);
        Hibernate.initialize(address.getContact());
        return address;
    }

    @Override
    @Transactional
    public Address create(Address addressJson) {
        return addressRepository.saveAndFlush(addressJson);
    }

    @Override
    @Transactional
    public Address update(Long id, Address addressJson) {
        Address updatedAddress = addressRepository.getById(id);
        modelMapper.map(addressJson, updatedAddress);
        return addressRepository.saveAndFlush(updatedAddress);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Address assignContact(Long addressId, Long contactId) {
        final Address address = addressRepository.getById(addressId);
        final Contact contact = contactService.get(contactId);
        //address.assignContact(contact);
        //addressRepository.assignContact(addressId, contactId);
        return address;
    }
}
