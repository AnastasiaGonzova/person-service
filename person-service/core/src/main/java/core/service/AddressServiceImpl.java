package core.service;

import core.api.service.AddressService;
import core.api.service.ContactService;
import core.mapper.AddressMapper;
import core.model.Address;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private AddressMapper addressMapper;
    private ContactService contactService;

    @Override
    public Address get(Long id) {
        return addressMapper.get(id);
    }

    @Override
    @Transactional
    public Address create(Address addressJson) {
        return addressMapper.create(addressJson);
    }

    @Override
    @Transactional
    public Address update(Long id, Address addressJson) {
        Address updatedAddress = addressMapper.get(id);
        if(addressJson.getCountryId() != null){
            updatedAddress.setCountryId(addressJson.getCountryId());
        }
        if(addressJson.getCity() != null){
            updatedAddress.setCity(addressJson.getCity());
        }
        if(addressJson.getPostCode() != null){
            updatedAddress.setPostCode(addressJson.getPostCode());
        }
        if(addressJson.getStreet() != null){
            updatedAddress.setStreet(addressJson.getStreet());
        }
        if(addressJson.getBuilding() != null){
            updatedAddress.setBuilding(addressJson.getBuilding());
        }
        if(addressJson.getFlat() != null){
            updatedAddress.setFlat(addressJson.getFlat());
        }
        return addressMapper.update(id, updatedAddress);
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
        return addressMapper.assignContact(addressId, contactId);
    }
}
