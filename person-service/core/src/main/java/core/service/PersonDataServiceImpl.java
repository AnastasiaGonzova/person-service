package core.service;

import core.api.service.ContactService;
import core.api.service.MedicalCardService;
import core.api.service.PersonDataService;
import core.mapper.PersonMapper;
import core.model.Contact;
import core.model.MedicalCard;
import core.model.PersonData;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PersonDataServiceImpl implements PersonDataService {

    private PersonMapper personMapper;
    private ContactService contactService;
    private MedicalCardService medicalCardService;

    @Override
    public PersonData get(Long id) {
        return personMapper.get(id);
    }

    @Override
    @Transactional
    public PersonData create(PersonData personDataJson) {
        return personMapper.create(personDataJson);
    }

    @Override
    @Transactional
    public PersonData update(Long id, PersonData personDataJson) {
        PersonData updatedPersonData = personMapper.get(id);
        if(personDataJson.getLastName()!= null){
            updatedPersonData.setLastName(personDataJson.getLastName());
        }
        if(personDataJson.getFirstName() != null){
            updatedPersonData.setFirstName(personDataJson.getFirstName());
        }
        if(personDataJson.getBirthDt() != null){
            updatedPersonData.setBirthDt(personDataJson.getBirthDt());
        }
        if(personDataJson.getAge() != null){
            updatedPersonData.setAge(personDataJson.getAge());
        }
        if(personDataJson.getSex() != null){
            updatedPersonData.setSex(personDataJson.getSex());
        }
        return personMapper.update(id, updatedPersonData);
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
        return personMapper.assignContact(personId, contactId);
    }

    @Override
    @Transactional
    public PersonData assignMedicalCard(Long personId, Long medicalCardId) {
        final MedicalCard medicalCard = medicalCardService.get(medicalCardId);
        final PersonData personData = personMapper.get(personId);
        personData.assignMedicalCard(medicalCard);
        return personMapper.assignMedicalCard(personId, medicalCardId);
    }

    @Override
    @Transactional
    public PersonData assignParent(Long personId, Long parentId) {
        final PersonData personData = personMapper.get(personId);
        final PersonData parentData = personMapper.get(parentId);
        personData.assignParent(parentData);
        return personMapper.assignParent(personId, parentId);
    }
}
