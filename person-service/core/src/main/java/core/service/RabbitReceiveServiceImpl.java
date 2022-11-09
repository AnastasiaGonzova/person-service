package core.service;

import core.api.service.PersonDataService;
import core.api.service.SignalService;
import core.model.PersonData;
import core.model.Signal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RabbitReceiveServiceImpl {

    private SignalService signalService;

    private PersonDataService personDataService;

    public void receiveMessage(String message, String queueName) {
        System.out.println("Получено сообщение " + message + " из очереди " + queueName);
        PersonData personData = personDataService.get(Long.getLong("1"));
        Signal signal = new Signal(null, message, personData);
        signalService.create(signal, Long.getLong("1"));

    }
}
