package core.service;

import core.api.service.AddressService;
import core.api.service.ContactService;
import core.api.mapper.AddressMapper;
import core.model.Address;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private AddressMapper addressMapper;
    private ContactService contactService;
    private ModelMapper modelMapper;

    @Override
    public Address get(Long id) {
        return addressMapper.get(id);
    }

    @Override
    @Transactional
    public Address create(Address addressJson) {
        addressMapper.create(addressJson);
        return addressJson;
    }

    @Override
    @Transactional
    public Address update(Long id, Address addressJson) {
        Address updatedAddress = addressMapper.get(id);
        modelMapper.map(addressJson, updatedAddress);
        addressMapper.update(id, updatedAddress);
        return updatedAddress;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressMapper.delete(id);
    }

    @Override
    @Transactional
    public Address assignContact(Long addressId, Long contactId) {
        final Address address = addressMapper.get(addressId);
        final Contact contact = contactService.get(contactId);
        address.assignContact(contact);
        addressMapper.assignContact(addressId, contactId);
        return address;
    }
}
