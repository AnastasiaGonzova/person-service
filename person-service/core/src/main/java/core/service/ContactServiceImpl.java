package core.service;

import core.api.service.ContactService;
import core.mapper.ContactMapper;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {

    private ContactMapper contactMapper;

    @Override
    public Contact get(Long id) {
        return contactMapper.get(id);
    }

    @Override
    @Transactional
    public Contact create(Contact contactJson) {
        return contactMapper.create(contactJson);
    }

    @Override
    @Transactional
    public Contact update(Long id, Contact contactJson) {
        Contact updatedContact = contactMapper.get(id);
        if(contactJson.getPhoneNumber() != null){
            updatedContact.setPhoneNumber(contactJson.getPhoneNumber());
        }
        if(contactJson.getEmail() != null){
            updatedContact.setEmail(contactJson.getEmail());
        }
        if(contactJson.getProfileLink() != null){
            updatedContact.setProfileLink(contactJson.getProfileLink());
        }
        return contactMapper.update(id, updatedContact);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        contactMapper.delete(id);
    }
}
