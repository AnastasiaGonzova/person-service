package core.service;

import core.api.service.ContactService;
import core.api.mapper.ContactMapper;
import core.model.Contact;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {

    private ContactMapper contactMapper;

    private ModelMapper modelMapper;

    @Override
    public Contact get(Long id) {
        return contactMapper.get(id);
    }

    @Override
    @Transactional
    public Contact create(Contact contactJson) {
        contactMapper.create(contactJson);
        return contactJson;
    }

    @Override
    @Transactional
    public Contact update(Long id, Contact contactJson) {
        Contact updatedContact = contactMapper.get(id);
        modelMapper.map(contactJson, updatedContact);
        contactMapper.update(id, updatedContact);
        return updatedContact;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        contactMapper.delete(id);
    }
}
