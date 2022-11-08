package core.api.service;

import core.model.PersonData;
import core.model.Signal;

public interface SignalService {

    Signal get(Long id);

    Signal getAndInitialize(Long id);

    Signal create(Signal signalJson);

    Signal update(Long id, Signal signalJson);

    void delete(Long id);
}
