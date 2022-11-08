package core.service;

import core.api.repository.ContactRepository;
import core.api.service.ContactService;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    private ModelMapper modelMapper;

    @Override
    public Contact get(Long id) {
        return contactRepository.getById(id);
    }

    @Override
    public Contact getAndInitialize(Long id) {
        Contact contact = contactRepository.getById(id);
        Hibernate.initialize(contact);
        Hibernate.initialize(contact.getPersonData());
        Hibernate.initialize(contact.getAddress());
        return contact;
    }

    @Override
    @Transactional
    public Contact create(Contact contactJson) {
        return contactRepository.saveAndFlush(contactJson);
    }

    @Override
    @Transactional
    public Contact update(Long id, Contact contactJson) {
        Contact updatedContact = contactRepository.getById(id);
        modelMapper.map(contactJson, updatedContact);
        return contactRepository.saveAndFlush(updatedContact);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
