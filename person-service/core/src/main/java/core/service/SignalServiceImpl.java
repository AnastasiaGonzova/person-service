package core.service;

import core.api.repository.SignalRepository;
import core.api.service.PersonDataService;
import core.api.service.SignalService;
import core.model.PersonData;
import core.model.Signal;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SignalServiceImpl implements SignalService {

    private SignalRepository signalRepository;
    private PersonDataService personDataService;
    private ModelMapper modelMapper;

    @Override
    public Signal get(Long id) {
        return signalRepository.getById(id);
    }

    @Override
    public Signal getAndInitialize(Long id) {
        Signal signal = signalRepository.getById(id);
        Hibernate.initialize(signal);
        Hibernate.initialize(signal.getPersonData());
        return signal;
    }

    @Override
    public Signal create(Signal signalJson, Long personId) {
        PersonData personData = personDataService.get(personId);
        signalJson.assignPersonData(personData);
        return signalRepository.saveAndFlush(signalJson);
    }

    @Override
    public Signal update(Long id, Signal signalJson) {
        Signal updatedSignal = signalRepository.getById(id);
        modelMapper.map(signalJson, updatedSignal);
        return signalRepository.saveAndFlush(updatedSignal);
    }

    @Override
    public void delete(Long signalId, Long personId) {
        PersonData personData = personDataService.get(personId);
        Signal signal = signalRepository.getById(signalId);
        signal.removePersonData(personData);
        signalRepository.getById(signalId);
    }
}
