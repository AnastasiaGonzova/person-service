package core.service;

import core.api.repository.PersonDataRepository;
import core.api.service.ContactService;
import core.api.service.MedicalCardService;
import core.api.service.PersonDataService;
import core.model.Contact;
import core.model.MedicalCard;
import core.model.PersonData;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PersonDataServiceImpl implements PersonDataService {

    private PersonDataRepository personDataRepository;
    private ContactService contactService;
    private MedicalCardService medicalCardService;

    private ModelMapper modelMapper;

    @Override
    public PersonData get(Long id) {
        return personDataRepository.getById(id);
    }

    @Override
    public PersonData getAndInitialize(Long id) {
        PersonData personData = personDataRepository.getById(id);
        Hibernate.initialize(personData);
        Hibernate.initialize(personData.getContact());
        Hibernate.initialize(personData.getMedicalCard());
        Hibernate.initialize(personData.getParents());
        Hibernate.initialize(personData.getUser());
        return personData;
    }

    @Override
    @Transactional
    public PersonData create(PersonData personDataJson) {
        return personDataRepository.saveAndFlush(personDataJson);
    }

    @Override
    @Transactional
    public PersonData update(Long id, PersonData personDataJson) {
        PersonData updatedPersonData = personDataRepository.getById(id);
        modelMapper.map(personDataJson, updatedPersonData);
        return personDataRepository.saveAndFlush(updatedPersonData);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personDataRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PersonData assignContact(Long personId, Long contactId) {
        final Contact contact = contactService.get(contactId);
        final PersonData personData = personDataRepository.getById(personId);
        //personData.assignContact(contact);
        //personDataRepository.assignContact(personId, contactId);
        return personData;
    }

    @Override
    @Transactional
    public PersonData assignMedicalCard(Long personId, Long medicalCardId) {
        final MedicalCard medicalCard = medicalCardService.get(medicalCardId);
        final PersonData personData = personDataRepository.getById(personId);
        //personData.assignMedicalCard(medicalCard);
        //personDataRepository.assignMedicalCard(personId, medicalCardId);
        return personData;
    }

    @Override
    @Transactional
    public PersonData assignParent(Long personId, Long parentId) {
        final PersonData personData = personDataRepository.getById(personId);
        final PersonData parentData = personDataRepository.getById(parentId);
        //personData.assignParent(parentData);
        //personDataRepository.assignParent(personId, parentId);
        return personData;
    }
}
