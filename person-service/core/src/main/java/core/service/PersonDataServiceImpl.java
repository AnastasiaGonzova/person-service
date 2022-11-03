package core.service;

import core.api.service.ContactService;
import core.api.service.MedicalCardService;
import core.api.service.PersonDataService;
import core.api.mapper.PersonMapper;
import core.model.Contact;
import core.model.MedicalCard;
import core.model.PersonData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PersonDataServiceImpl implements PersonDataService {

    private PersonMapper personMapper;
    private ContactService contactService;
    private MedicalCardService medicalCardService;

    private ModelMapper modelMapper;

    @Override
    public PersonData get(Long id) {
        return personMapper.get(id);
    }

    @Override
    @Transactional
    public PersonData create(PersonData personDataJson) {
        personMapper.create(personDataJson);
        return personDataJson;
    }

    @Override
    @Transactional
    public PersonData update(Long id, PersonData personDataJson) {
        PersonData updatedPersonData = personMapper.get(id);
        modelMapper.map(personDataJson, updatedPersonData);
        personMapper.update(id, updatedPersonData);
        return updatedPersonData;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personMapper.delete(id);
    }

    @Override
    @Transactional
    public PersonData assignContact(Long personId, Long contactId) {
        final Contact contact = contactService.get(contactId);
        final PersonData personData = personMapper.get(personId);
        personData.assignContact(contact);
        personMapper.assignContact(personId, contactId);
        return personData;
    }

    @Override
    @Transactional
    public PersonData assignMedicalCard(Long personId, Long medicalCardId) {
        final MedicalCard medicalCard = medicalCardService.get(medicalCardId);
        final PersonData personData = personMapper.get(personId);
        personData.assignMedicalCard(medicalCard);
        personMapper.assignMedicalCard(personId, medicalCardId);
        return personData;
    }

    @Override
    @Transactional
    public PersonData assignParent(Long personId, Long parentId) {
        final PersonData personData = personMapper.get(personId);
        final PersonData parentData = personMapper.get(parentId);
        personData.assignParent(parentData);
        personMapper.assignParent(personId, parentId);
        return personData;
    }
}
