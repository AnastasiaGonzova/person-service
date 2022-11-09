package core.api.service;

import core.model.PersonData;
import core.model.Signal;

public interface SignalService {

    Signal get(Long id);

    Signal getAndInitialize(Long id);

    Signal create(Signal signalJson, Long personId);

    Signal update(Long id, Signal signalJson);

    void delete(Long signalId, Long personId);
}
